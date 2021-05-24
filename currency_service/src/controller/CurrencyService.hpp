#include "../dto/CurrencyData.hpp"
#include "../client/CurrencyClient.hpp"
#include "./StructTmExtension.hpp"
#include "oatpp/web/server/api/ApiController.hpp"
#include "oatpp/core/macro/codegen.hpp"
#include "oatpp/core/macro/component.hpp"
#include "oatpp/core/Types.hpp"

#include <iostream>
#include <sstream>
#include <fstream>
#include <string>


#include OATPP_CODEGEN_BEGIN(ApiController)

class CurrencyService: public oatpp::web::server::api::ApiController {
public:
    CurrencyService(OATPP_COMPONENT(std::shared_ptr<ObjectMapper>, jsonMapper)) 
        : oatpp::web::server::api::ApiController(jsonMapper) {}

    ENDPOINT("GET", "/", root)
    {
        return rootSite();
    }

    ENDPOINT("GET", "/predict", createSetupData, 
        QUERY(String, startDate),
        QUERY(String, endDate),
        QUERY(Int32, daysToPredict),
        QUERY(String, initialCurrency),
        QUERY(String, targetCurrency)
    )
    {   
        return gatherAndPredict(startDate, endDate, daysToPredict, initialCurrency, targetCurrency);
    }
    
private:
    OATPP_COMPONENT(std::shared_ptr<CurrencyClient>, currencyClient);
    OATPP_COMPONENT(std::shared_ptr<ObjectMapper>, jsonMapper);

    std::string accessKey = "<subscribe to get api key>";

    std::string loadSite(const std::string& pagePath);
    
    std::shared_ptr<OutgoingResponse> gatherAndPredict(
        String startDate, String endDate, Int32 daysToPredict, String initialCurrency, String targetCurrency
    );

    std::shared_ptr<OutgoingResponse> predict(
        const Fields<Float64>& ratesPerDate, struct tm& beginPredictionDateTm, 
        Int32 daysToPredict, String initialCurrency, String targetCurrency
    );

    std::shared_ptr<OutgoingResponse> rootSite();

    std::shared_ptr<OutgoingResponse> errorSite(const std::string& errorMsg);

    std::shared_ptr<OutgoingResponse> resultSite(const std::string& resultBuffer);

};

#include OATPP_CODEGEN_END(ApiController)