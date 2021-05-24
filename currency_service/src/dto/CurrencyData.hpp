#include "oatpp/core/macro/codegen.hpp"
#include "oatpp/core/Types.hpp"

#include OATPP_CODEGEN_BEGIN(DTO)

class CurrencyData: public oatpp::DTO {
    DTO_INIT(CurrencyData, DTO);

    DTO_FIELD(Boolean, success);
    DTO_FIELD(Int64, timestamp);
    DTO_FIELD(String, base);
    DTO_FIELD(UnorderedFields<Float64>, rates);
    DTO_FIELD(String, date);
};

#include OATPP_CODEGEN_END(DTO)