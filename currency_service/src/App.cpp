#include "./AppComponents.hpp"
#include "oatpp/network/Server.hpp"
#include <iostream>


void run(){
    AppComponents components;
    OATPP_COMPONENT(std::shared_ptr<oatpp::web::server::HttpRouter>, router);
    OATPP_COMPONENT(std::shared_ptr<CurrencyService>, serviceApi);
    
    serviceApi->addEndpointsToRouter(router);

    OATPP_COMPONENT(std::shared_ptr<oatpp::network::ConnectionHandler>, connectionHandler);
    OATPP_COMPONENT(std::shared_ptr<oatpp::network::ServerConnectionProvider>, connectionProvider);

    oatpp::network::Server server(connectionProvider, connectionHandler);
    server.run();
}

int main(){
    oatpp::base::Environment::init();
    run();
    oatpp::base::Environment::destroy();
    return 0;
}
