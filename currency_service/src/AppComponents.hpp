#ifndef AppComponents_hpp
#define AppComponents_hpp

#include "./controller/CurrencyService.hpp"
#include "oatpp/web/server/HttpConnectionHandler.hpp"
#include "oatpp/network/tcp/server/ConnectionProvider.hpp"
#include "oatpp/parser/json/mapping/ObjectMapper.hpp"
#include "oatpp/core/macro/component.hpp"
#include "oatpp-curl/RequestExecutor.hpp"


class AppComponents {
public:
    OATPP_CREATE_COMPONENT(std::shared_ptr<oatpp::network::ServerConnectionProvider>, serverConnectionProvider)([] {
        return oatpp::network::tcp::server::ConnectionProvider::createShared({"0.0.0.0", 8000, oatpp::network::Address::IP_4});
    }());
  
    OATPP_CREATE_COMPONENT(std::shared_ptr<oatpp::web::server::HttpRouter>, httpRouter)([] {
        return oatpp::web::server::HttpRouter::createShared();
    }());
  
    OATPP_CREATE_COMPONENT(std::shared_ptr<oatpp::network::ConnectionHandler>, serverConnectionHandler)([] {
        OATPP_COMPONENT(std::shared_ptr<oatpp::web::server::HttpRouter>, httpRouter); 
        return oatpp::web::server::HttpConnectionHandler::createShared(httpRouter);
    }());
  
    OATPP_CREATE_COMPONENT(std::shared_ptr<oatpp::data::mapping::ObjectMapper>, jsonMapper)([] {
        return oatpp::parser::json::mapping::ObjectMapper::createShared();
    }());

    OATPP_CREATE_COMPONENT(std::shared_ptr<oatpp::curl::RequestExecutor>, clientRequestExecutor)([] {
        return oatpp::curl::RequestExecutor::createShared("https://api.exchangeratesapi.io", false);
    }());

    OATPP_CREATE_COMPONENT(std::shared_ptr<CurrencyClient>, currencyClient)([] {
        OATPP_COMPONENT(std::shared_ptr<oatpp::curl::RequestExecutor>, clientRequestExecutor);
        OATPP_COMPONENT(std::shared_ptr<oatpp::data::mapping::ObjectMapper>, jsonMapper);
        return std::make_shared<CurrencyClient>(clientRequestExecutor, jsonMapper);
    }());

    OATPP_CREATE_COMPONENT(std::shared_ptr<CurrencyService>, serviceApi)([] {
        OATPP_COMPONENT(std::shared_ptr<oatpp::data::mapping::ObjectMapper>, jsonMapper);
        return std::make_shared<CurrencyService>(jsonMapper);
    }());
};

#endif