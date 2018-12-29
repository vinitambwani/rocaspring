CREATE TABLE [dbo].industry_parent_master
(
	[industry_id] INT NOT NULL PRIMARY KEY, 
    [industry_name] VARCHAR(MAX) NOT NULL, 
    [industry_type] VARCHAR(MAX) NULL, 
    [is_display] INT NULL DEFAULT 1
);

CREATE TABLE [dbo].industry_child_master
(
	[industry_child_Id] INT NOT NULL PRIMARY KEY, 
    [industry_parent_id] INT NULL, 
    [industry_name] VARCHAR(MAX) NULL, 
    [is_display] INT NULL DEFAULT 1, 
    CONSTRAINT [FK_industry_parent_id] FOREIGN KEY (industry_parent_id) REFERENCES [dbo].industry_parent_master([industry_id])
)
