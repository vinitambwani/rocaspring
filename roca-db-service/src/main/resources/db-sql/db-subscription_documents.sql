CREATE TABLE [dbo].[subscription_documents] (
    [subscription_doc_id] INT             IDENTITY (1, 1) NOT NULL,
    [subscription_id]     INT             NULL,
    [doc_name]            VARCHAR (MAX)   NULL,
    [type]                VARCHAR (50)    NULL,
    [doc_data]            VARBINARY (MAX) NULL,
    [emailid]             VARCHAR (MAX)   NULL,
    [file_extention] VARCHAR(MAX) NULL, 
    PRIMARY KEY CLUSTERED ([subscription_doc_id] ASC)
);

