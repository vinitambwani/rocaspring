create schema rocaserviceteam;
create schema rocamaster;
create schema rocausers;

CREATE TABLE rocamaster.Country
(
	[Id] INT IDENTITY(1,1) NOT NULL PRIMARY KEY, 
    [CountryName] VARCHAR(50) NOT NULL, 
    [isActive] INT NOT NULL DEFAULT 1, 
    [CreatedBy] VARCHAR(50) NULL, 
    [CreatedOn] DATETIME NOT NULL DEFAULT GETDATE(), 
    [UpdatedBy] VARCHAR(50) NULL, 
    [UpdatedOn] DATETIME NULL DEFAULT GETDATE(),

)


CREATE TABLE [RocaMaster].[CityMaster] (
    [Id]          INT           IDENTITY (1, 1) NOT NULL,
    [CityName] VARCHAR(50) NOT NULL,
    [CountryId] INT ,
    [isActive] INT NOT NULL DEFAULT 1, 
    [CreatedBy] VARCHAR(50) NULL, 
    [CreatedOn] DATETIME NOT NULL DEFAULT GETDATE(), 
    [UpdatedBy] VARCHAR(50) NULL, 
    [UpdatedOn] DATETIME NULL DEFAULT GETDATE(),
    PRIMARY KEY CLUSTERED ([Id] ASC),
      CONSTRAINT [FkCountryId] FOREIGN KEY (CountryId) REFERENCES [RocaMaster].[Country](Id)
);


--ROLEMASTER
CREATE TABLE [RocaMaster].[RoleMaster] (
    [Id]          INT           IDENTITY (1, 1) NOT NULL,
    [Name] VARCHAR(50) NOT NULL,
    [isActive] INT NOT NULL DEFAULT 1, 
    [CreatedBy] VARCHAR(50) NULL, 
    [CreatedOn] DATETIME NOT NULL DEFAULT GETDATE(), 
    [UpdatedBy] VARCHAR(50) NULL, 
    [UpdatedOn] DATETIME NULL DEFAULT GETDATE(),
    PRIMARY KEY CLUSTERED ([Id] ASC)
);
CREATE TABLE [RocaMaster].[IndustryParentMaster] (
    [Id]          INT           IDENTITY (1, 1) NOT NULL,
    [Name] VARCHAR(50) NOT NULL,
    [isActive] INT NOT NULL DEFAULT 1, 
    [CreatedBy] VARCHAR(50) NULL, 
    [CreatedOn] DATETIME NOT NULL DEFAULT GETDATE(), 
    [UpdatedBy] VARCHAR(50) NULL, 
    [UpdatedOn] DATETIME NULL DEFAULT GETDATE(),
    PRIMARY KEY CLUSTERED ([Id] ASC)
);
CREATE TABLE [RocaMaster].[IndustryChildMaster] (
    [Id]          INT           IDENTITY (1, 1) NOT NULL,
    [Name] VARCHAR(50) NOT NULL,
	[IndustryParentId] INT,
    [isActive] INT NOT NULL DEFAULT 1, 
    [CreatedBy] VARCHAR(50) NULL, 
    [CreatedOn] DATETIME NOT NULL DEFAULT GETDATE(), 
    [UpdatedBy] VARCHAR(50) NULL, 
    [UpdatedOn] DATETIME NULL DEFAULT GETDATE(),
    PRIMARY KEY CLUSTERED ([Id] ASC),
	CONSTRAINT [FkIndustryParentId] FOREIGN KEY (IndustryParentId) REFERENCES [RocaMaster].[IndustryParentMaster](Id)
);

CREATE TABLE [rocausers].[RocaUserRegistration] (
    [Id]                 INT           NOT NULL,
    [LegalEntityName]  VARCHAR (255) NULL,
    [contactperson]    VARCHAR (255) NULL,
    [roleId]           INT           NOT NULL,
    [emailid]          VARCHAR (255) NOT NULL UNIQUE,
    [mobileNumber]     NUMERIC (10)  NULL,
    [industryId]       VARCHAR(255)  NULL, -- storing all industryId as comma separated..
    [password]         VARCHAR (10)  NULL,
    [isemailVerified]  INT           NULL,
    [ismobileverified] INT           NULL,
	[isActive] INT NOT NULL DEFAULT 1, 
    [CreatedBy] VARCHAR(50) NULL, 
    [CreatedOn] DATETIME NOT NULL DEFAULT GETDATE(), 
    [UpdatedBy] VARCHAR(50) NULL, 
    [UpdatedOn] DATETIME NULL DEFAULT GETDATE(),
	PRIMARY KEY CLUSTERED ([Id] ASC),
	CONSTRAINT [FkRoleId] FOREIGN KEY (roleId) REFERENCES [RocaMaster].[RoleMaster](Id));


	
CREATE TABLE [rocausers].[Subscription] (
    [Id]                 INT           IDENTITY (1, 1) NOT NULL,
    [LegalEntityName]               VARCHAR  (50) NULL,
    [Pseudonym]                       VARCHAR (50) NULL,
    [CountryIncorporation]           INT           NULL,
    [TaxResidentialStatus]          INT           NULL,
    [BodyCorporates]                 VARCHAR (50) NULL,
    [IsCharitableOrNonProfitable] INT           DEFAULT ((1)) NULL,
    [RegistationId]                   INT           NULL,
    [EmailId]                         VARCHAR (50) NULL UNIQUE,
    [IndustryId]                        VARCHAR (50) NULL,
    [CompanyHQLocation]             int NOT NULL,
    [Pan]                             VARCHAR (50) NOT NULL,
    [IsPanAttached]                 INT           DEFAULT ((0)) NOT NULL,
    [PanComments]                    VARCHAR (50) NULL,
    [Gst]                             VARCHAR (50) NOT NULL,
    [GstComments]                    VARCHAR (50) NULL,
    [Url]                             VARCHAR (50) NULL,
    [Address]                         VARCHAR (50) NULL,
    [IsEyDisclosureAccepted]       INT           DEFAULT ((0)) NOT NULL,
    [Status]                          VARCHAR (50) DEFAULT ('NEW') NOT NULL,
    [PaceId]                         VARCHAR (50) NULL,
    [IsAdditionalDocRequired]      INT           DEFAULT ((0)) NOT NULL,
    [IsOnlineEngagedSigned]        INT           DEFAULT ((0)) NOT NULL,
    [RoleId]                         INT           NULL,
    [WorkedWithEY] INT NULL DEFAULT 0, 
    [EYContactPerson1] VARCHAR(50) NULL, 
    [EYContactPerson2] VARCHAR(50) NULL, 
    [IsRocaServiceAvailed] INT NULL DEFAULT 0, 
    [RelatedPartyName1] VARCHAR(50) NULL, 
    [RelatedPartyName2] VARCHAR(50) NULL, 
	[isActive] INT NOT NULL DEFAULT 1, 
    [CreatedBy] VARCHAR(50) NULL, 
    [CreatedOn] DATETIME NOT NULL DEFAULT GETDATE(), 
    [UpdatedBy] VARCHAR(50) NULL, 
    [UpdatedOn] DATETIME NULL DEFAULT GETDATE(),
    PRIMARY KEY CLUSTERED ([Id] ASC),
	CONSTRAINT [FkSubscriptionRoleId] FOREIGN KEY (roleId) REFERENCES [RocaMaster].[RoleMaster](Id),
	CONSTRAINT [FkCompanyHQLocation] FOREIGN KEY (CompanyHQLocation) REFERENCES [RocaMaster].[Country](Id),
	CONSTRAINT [FkCountryIncorporation] FOREIGN KEY (CountryIncorporation) REFERENCES [RocaMaster].[Country](Id)
);

CREATE TABLE Rocausers.[SubscriptionDocuments] (
    [Id] 				 INT  IDENTITY (1, 1) NOT NULL,
    [SubscriptionId]     INT             NOT NULL,
    [DocName]            VARCHAR (MAX)   NOT NULL,
    [type]               VARCHAR (50)    NOT NULL,  --TODO : GST,PAN,ADDITIONAL DOCS or we can create enum in java to store ..
    --[doc_data]           VARBINARY (MAX) NOT NULL,
	[ContainerName]      VARCHAR (50)  NOT NULL,
    [emailid]            VARCHAR (MAX)   NOT NULL,
    [file_extention] VARCHAR(MAX) NULL, 
	[isActive] INT NOT NULL DEFAULT 1, 
    [CreatedBy] VARCHAR(50) NULL, 
    [CreatedOn] DATETIME NOT NULL DEFAULT GETDATE(), 
    [UpdatedBy] VARCHAR(50) NULL, 
    [UpdatedOn] DATETIME NULL DEFAULT GETDATE(),
	PRIMARY KEY CLUSTERED ([Id] ASC),
	CONSTRAINT [FkSubscriptionId] FOREIGN KEY (SubscriptionId) REFERENCES [Rocausers].[Subscription](Id)
);

CREATE TABLE [RocaMaster].SubscritpionDetailsMaster
(
	Id INT IDENTITY(1,1) NOT NULL PRIMARY KEY, 
    SubscriptionCatgory VARCHAR(50) NULL, 
    [isActive] INT NOT NULL DEFAULT 1, 
    [CreatedBy] VARCHAR(50) NULL, 
    [CreatedOn] DATETIME NOT NULL DEFAULT GETDATE(), 
    [UpdatedBy] VARCHAR(50) NULL, 
    [UpdatedOn] DATETIME NULL DEFAULT GETDATE()
);

CREATE TABLE [RocaUsers].TransactionSubscribtionDetails
(
	[Id] INT NOT NULL PRIMARY KEY, 
    [subscriptionId] INT NOT NULL, 
    [SubscriptionDetailsId] INT NULL, 
     [isActive] INT NOT NULL DEFAULT 1, 
    [CreatedBy] VARCHAR(50) NULL, 
    [CreatedOn] DATETIME NOT NULL DEFAULT GETDATE(), 
    [UpdatedBy] VARCHAR(50) NULL, 
    [UpdatedOn] DATETIME NULL DEFAULT GETDATE()
    CONSTRAINT [FKTransactionSubId] FOREIGN KEY (subscriptionId) REFERENCES [Rocausers].[subscription](Id),
	CONSTRAINT [FKDetailsId] FOREIGN KEY (SubscriptionDetailsId) REFERENCES [RocaMaster].[SubscritpionDetailsMaster](Id)

);


CREATE TABLE [RocaUsers].Query
(
	[Id] INT NOT NULL PRIMARY KEY, 
    [QueryCaption] VARCHAR(MAX) NOT NULL, 
    [QueryFact] VARCHAR(MAX) NOT NULL, 
    [ETA] VARCHAR(MAX) NULL, 
    [Priority] VARCHAR(MAX) NULL, 
    [Category] VARCHAR(MAX) NULL, 
    [FinancialYear] VARCHAR(MAX) NULL, 
    [Status] VARCHAR(MAX) NOT NULL DEFAULT 'NEW', 
    [IsAssigned] INT NOT NULL DEFAULT 0, 
    [InScope] INT NOT NULL DEFAULT 1, 
    [Comments] VARCHAR(MAX) NULL,
	[isActive] INT NOT NULL DEFAULT 1, 
    [CreatedBy] VARCHAR(50) NULL, 
    [CreatedOn] DATETIME NOT NULL DEFAULT GETDATE(), 
    [UpdatedBy] VARCHAR(50) NULL, 
    [UpdatedOn] DATETIME NULL DEFAULT GETDATE()
);

CREATE TABLE [RocaUsers].Question
(
	Id INT IDENTITY(1,1) NOT NULL PRIMARY KEY, 
    [QuestionDescription] VARCHAR(MAX) NULL, 
    [QueryId] INT NOT NULL, 
    [Status] VARCHAR(50),
    [ModifiedQuestionDescription] VARCHAR(MAX) NULL, 
    [IsQuestionModified] INT NOT NULL DEFAULT 0, 
    [Commnets] VARCHAR(MAX) NULL,
	[isActive] INT NOT NULL DEFAULT 1, 
    [CreatedBy] VARCHAR(50) NULL, 
    [CreatedOn] DATETIME NOT NULL DEFAULT GETDATE(), 
    [UpdatedBy] VARCHAR(50) NULL, 
    [UpdatedOn] DATETIME NULL DEFAULT GETDATE(),
    CONSTRAINT [FKQueryId] FOREIGN KEY (QueryId) REFERENCES [Rocausers].[Query](Id),

);


CREATE TABLE [rocaserviceteam].[QueryAssignment] (
    [Id] INT           NOT NULL,
    [QueryId]      INT           NULL,
    [FromAssignment] VARCHAR (MAX) NULL,
    [ToAssignment]     VARCHAR (MAX) NULL,
    [Comments]      VARCHAR (MAX) NULL,
	[IsActive] INT NOT NULL DEFAULT 1, 
    [CreatedBy] VARCHAR(50) NULL, 
    [CreatedOn] DATETIME NOT NULL DEFAULT GETDATE(), 
    [UpdatedBy] VARCHAR(50) NULL, 
    [UpdatedOn] DATETIME NULL DEFAULT GETDATE(),
    PRIMARY KEY CLUSTERED ([Id] ASC),
    CONSTRAINT [FKQueryAssignmentFK] FOREIGN KEY ([QueryId]) REFERENCES [rocausers].[Query] ([Id])
);

CREATE TABLE [rocaserviceteam].SubscriptionAssignment
(
	[Id] INT IDENTITY(1,1) NOT NULL PRIMARY KEY, 
    [SubscriptionId] INT NULL, 
    [FromAssignment] VARCHAR(50) NULL, 
    [ToAssignment] VARCHAR(50) NULL, 
    [Comments] VARCHAR(255) NULL, 
    [IsActive] INT NOT NULL DEFAULT 1, 
    [CreatedBy] VARCHAR(50) NULL, 
    [CreatedOn] DATETIME NOT NULL DEFAULT GETDATE(), 
    [UpdatedBy] VARCHAR(50) NULL, 
    [UpdatedOn] DATETIME NULL DEFAULT GETDATE(),
	CONSTRAINT [FKTransactionSubId] FOREIGN KEY (SubscriptionId) REFERENCES [Rocausers].[subscription](Id)
);





