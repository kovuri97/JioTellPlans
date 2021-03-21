create table Plans(planId int primary key AUTO_INCREMENT, planCategory varchar(50),
price double, planValidity varchar(50), planData varchar(50), dataSpeed varchar(50), voice varchar(50),
sms varchar(50), otherSubscription varchar(50));
create table CutomerPlanDetails(customePlanId long primary key AUTO_INCREMENT, customeId int,planId int primary key AUTO_INCREMENT, planCategory varchar(50),
price double, planValidity varchar(50), planData varchar(50), dataSpeed varchar(50), voice varchar(50),
sms varchar(50), otherSubscription varchar(50),status varchar(50), date varchar(50), planAvailableDays long);
