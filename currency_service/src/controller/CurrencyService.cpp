#include "./CurrencyService.hpp"

std::string CurrencyService::loadSite(const std::string& pagePath)
{
    std::ifstream inFile;
    std::stringstream strStream;

    inFile.open(pagePath); 
    strStream << inFile.rdbuf();

    std::string pageContent = strStream.str();
    return pageContent;
}

std::shared_ptr<oatpp::web::protocol::http::outgoing::Response> CurrencyService::rootSite()
{
    String pageContent = loadSite("../websites/root.html").c_str();
    return createResponse(Status::CODE_200, pageContent);
}

std::shared_ptr<oatpp::web::protocol::http::outgoing::Response> CurrencyService::errorSite(
    const std::string& errorMsg
)
{
    String errorSite = (
        loadSite("../websites/error_begin.html") + errorMsg + loadSite("../websites/error_end.html")
    ).c_str();
    return createResponse(Status::CODE_400, errorSite);
}

std::shared_ptr<oatpp::web::protocol::http::outgoing::Response> CurrencyService::resultSite(
    const std::string& resultBuffer
)
{
    String resultSite = (
        loadSite("../websites/result_begin.html") + resultBuffer + loadSite("../websites/result_end.html")
    ).c_str();
    return createResponse(Status::CODE_200, resultSite);
}

std::shared_ptr<oatpp::web::protocol::http::outgoing::Response> CurrencyService::gatherAndPredict(
    String startDate, String endDate, Int32 daysToPredict, String initialCurrency, String targetCurrency
)
{
    struct tm startDateTm, endDateTm;
    strptime(startDate.get()->c_str(), "%Y-%m-%d", &startDateTm);
    strptime(endDate.get()->c_str(), "%Y-%m-%d", &endDateTm);

    if(daysToPredict <= 0 || startDateTm > endDateTm)
    {
        std::string errorMsg = "Invalig arguments: negative days to predict or start date greater than end date";
        return errorSite(errorMsg);
    }
    auto ratesPerDate = Fields<Float64>::createShared();
    
    for(++endDateTm; startDateTm != endDateTm; ++startDateTm)
    {
        char dateBuffer[100];
        strftime(dateBuffer, 100, "%Y-%m-%d", &startDateTm);
        String currentDate = dateBuffer;
    
        auto response = currencyClient->getChangeData(currentDate, accessKey.c_str(), initialCurrency, targetCurrency);
        if(response->getStatusCode() != 200)
        {
            std::string errorMsg = response->readBodyToString().get()->std_str();
            return errorSite(errorMsg);
        }
        Float64 rate = response->readBodyToDto<Object<CurrencyData>>(&(*jsonMapper))->rates->find(targetCurrency)->second;
        ratesPerDate->push_back({currentDate, rate});
    }

    return predict(ratesPerDate, endDateTm, daysToPredict, initialCurrency, targetCurrency);
}

std::shared_ptr<oatpp::web::protocol::http::outgoing::Response> CurrencyService::predict(
    const Fields<Float64>& ratesPerDate, struct tm& beginPredictionDateTm, 
    Int32 daysToPredict, String initialCurrency, String targetCurrency
)
{
    double dayCount, sumX, sumY, sumXY, sumX2 = 0.0;
    
    for(auto& rateDatePair: *ratesPerDate)
    {
        dayCount++;
        sumX += dayCount;
        sumY += rateDatePair.second;
        sumX2 += dayCount * dayCount;
        sumXY += dayCount * rateDatePair.second;
    }
    
    double coeffA = (sumY * sumX2 - sumX * sumXY) / (dayCount * sumX2 - sumX * sumX);
    double coeffB = (dayCount * sumXY - sumX * sumY) / (dayCount * sumX2 - sumX * sumX);
    
    std::string predictions;
    for(int day = 0; day < daysToPredict; day++)
    {
        dayCount++;
        double prediction = coeffA + coeffB * dayCount;
        
        char dateBuffer[100];
        strftime(dateBuffer, 100, "%Y-%m-%d", &beginPredictionDateTm);
        std::string currentDate = dateBuffer;

        predictions += "<p> Date: " + currentDate + " Predicted rate: " + std::to_string(prediction) + " </p>";
        ++beginPredictionDateTm;
    }
    return resultSite(predictions);
}
