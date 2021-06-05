#include "HelperTypes.ice"

module DynamicExample
{
    interface DynamicInvocations
    {
        HelperTypes::GroupedIntegersDict groupByKey(HelperTypes::DictSeq seq);

        HelperTypes::GroupedStringDict reverseDict(HelperTypes::StrToIntDict dict);

        HelperTypes::CountDict countOccurences(HelperTypes::IntegerGroup group);
    };
};