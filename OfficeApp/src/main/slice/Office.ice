#include "Notification.ice"

module Office
{
    struct basicCaseData
    {
        int uniqueID;
        Notification::timeStamp registrationTime;
    };

    interface Reception
    {
        void register(int uniqueID, Notification::Notifier* notifier);
    };

    interface Service
    {
        int requestUnemployedAid(basicCaseData data, float averageEarnings);

        int requestBuildingPermission(basicCaseData data, string address, float area);

        int requestDrivingLicence(basicCaseData data, long candidateProfile, bool isFirstLicence);
    };
};