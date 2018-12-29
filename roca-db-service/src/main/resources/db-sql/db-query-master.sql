CREATE TABLE [dbo].Query_Master
(
	[Query_ID] INT NOT NULL PRIMARY KEY, 
    [Query_Caption] VARCHAR(MAX) NULL, 
    [Query_Fact] VARCHAR(MAX) NULL, 
    [Create_by_user] VARCHAR(MAX) NULL, 
    [Create_date] TIMESTAMP NULL , 
    [ETA] VARCHAR(MAX) NULL, 
    [priority] VARCHAR(MAX) NULL, 
    [category] VARCHAR(MAX) NULL, 
    [financial year] VARCHAR(MAX) NULL, 
    [status] VARCHAR(MAX) NULL DEFAULT 'NEW', 
    [is_assigned] INT NOT NULL DEFAULT 0, 
    [in_scope] INT NOT NULL DEFAULT 1, 
    [Comments] VARCHAR(MAX) NULL
)
