CREATE TABLE [dbo].Question_MASTER
(
	Qestion_id INT IDENTITY(1,1) NOT NULL PRIMARY KEY, 
    [Question_description] VARCHAR(MAX) NULL, 
    [Query_id] INT NULL, 
    [created_on] TIMESTAMP NULL, 
    [created_by] VARCHAR(MAX) NULL, 
    [Status] NCHAR(10) NULL, 
    [modified_question_description] VARCHAR(MAX) NULL, 
    [is_question_modified] INT NOT NULL DEFAULT 0, 
    [Commnets] VARCHAR(MAX) NULL
)
