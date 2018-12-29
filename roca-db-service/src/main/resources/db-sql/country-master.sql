CREATE TABLE [RocaMaster].[CountryMaster] (
    [Id]          INT           IDENTITY (1, 1) NOT NULL,
    [CountryName] VARCHAR(200) NOT NULL,
    [	]                     INT           DEFAULT 1,
    [CreatedOn] TIMESTAMP ,
    []
    PRIMARY KEY CLUSTERED ([Id] ASC)
);

CREATE TABLE [RocaMaster].[CityMaster] (
    [Id]          INT           IDENTITY (1, 1) NOT NULL,
    [CityName] VARCHAR(200) NOT NULL,
    [CountryId] INT ,
    [isActive] INT NOT NULL DEFAULT 1, 
    [CreatedBy] VARCHAR(50) NULL, 
    [CreatedOn] DATETIME NOT NULL DEFAULT GETDATE(), 
    [UpdatedBy] VARCHAR(50) NULL, 
    [UpdatedOn] DATETIME NULL DEFAULT GETDATE(),
    PRIMARY KEY CLUSTERED ([Id] ASC),
      CONSTRAINT [FkCountryId] FOREIGN KEY (CountryId) REFERENCES [CountryMaster](Id)
);