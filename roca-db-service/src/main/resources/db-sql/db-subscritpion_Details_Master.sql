CREATE TABLE [dbo].subscritpion_Details_Master
(
	subscription_details_id INT IDENTITY(1,1) NOT NULL PRIMARY KEY, 
    [subscription_catgory] VARCHAR(MAX) NULL, 
    [is_active] INT NOT NULL DEFAULT 1,

)
