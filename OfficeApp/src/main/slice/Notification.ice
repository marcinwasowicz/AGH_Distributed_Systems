module Notification
{
    struct timeStamp
    {
        int hour;
        int minute;
        int second;
    };

    struct caseResult
    {
        timeStamp registrationTimeStamp;
        int expectedResolutionTime;
        string resultMessage;
    };

    sequence<caseResult> batchedResults;

    interface Notifier
    {
        void singleNotify(caseResult result);
        void batchedNotify(batchedResults results);
    };
};