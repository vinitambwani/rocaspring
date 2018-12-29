CREATE TABLE [dbo].Subscription_assignment
(
	assignment_id INT IDENTITY(1,1) NOT NULL PRIMARY KEY, 
    [subscription_id] INT NULL, 
    [from_assignment] VARCHAR(MAX) NULL, 
    [to_Assignment] VARCHAR(MAX) NULL, 
    [comments] VARCHAR(MAX) NULL, 
    [created_date] TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP
)
