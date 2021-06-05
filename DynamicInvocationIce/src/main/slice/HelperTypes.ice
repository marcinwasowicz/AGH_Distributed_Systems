module HelperTypes
{
    sequence <int> IntegerGroup;

    sequence <string> StringGroup;

    dictionary <int, int> CountDict;

    dictionary <string, int> StrToIntDict;

    dictionary <string, IntegerGroup> GroupedIntegersDict;

    dictionary <int, StringGroup> GroupedStringDict;

    sequence<StrToIntDict> DictSeq;
};