#include "oatpp/web/client/ApiClient.hpp"
#include "oatpp/core/macro/codegen.hpp"

#include OATPP_CODEGEN_BEGIN(ApiClient)

class CurrencyClient: public oatpp::web::client::ApiClient {
    API_CLIENT_INIT(CurrencyClient);
    
    API_CALL("GET", "/v1/{date}", getChangeData, 
        PATH(String, date),
        QUERY(String, access_key),
        QUERY(String, base),
        QUERY(String, symbols)
    );
};

#include OATPP_CODEGEN_END(ApiClient)