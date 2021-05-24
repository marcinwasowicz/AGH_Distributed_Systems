#include <time.h>

inline bool operator != (const struct tm& tm1, const struct tm& tm2)
{
    return tm1.tm_mday != tm2.tm_mday || tm1.tm_mon != tm2.tm_mon || tm1.tm_year != tm2.tm_year;
}

inline bool operator > (const struct tm& tm1, const struct tm& tm2)
{
    return tm1.tm_year > tm2.tm_year || tm1.tm_mon > tm2.tm_mon || tm1.tm_mday > tm2.tm_mday;
}

inline void operator ++ (struct tm& date)
{
    int currentMonthDays;
    date.tm_mday++;
    if(date.tm_mon % 2 == 1)
    {
        currentMonthDays = 31;
    }
    else if(date.tm_mon == 2 && date.tm_year % 4 == 0)
    {
        currentMonthDays = 29;
    }
    else if(date.tm_mon == 2)
    {
        currentMonthDays = 28;
    }
    else
    {
        currentMonthDays = 30;
    }
    if(date.tm_mday > currentMonthDays)
    {
        date.tm_mday = 1;
        date.tm_mon++;
    }
    if (date.tm_mon > 12)
    {
        date.tm_mon = 1;
        date.tm_mday = 1;
        date.tm_year++;
    }
}