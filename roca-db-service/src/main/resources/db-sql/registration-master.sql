CREATE TABLE [dbo].[roles_registration] (
    [role_registration_id]          INT           IDENTITY (1, 1) NOT NULL,
    [role_registration_description] VARCHAR(200) NOT NULL,
    [is_active]                     INT           NULL,
    PRIMARY KEY CLUSTERED ([role_registration_id] ASC)
);

SET IDENTITY_INSERT [dbo].[roles_registration] ON
INSERT INTO [dbo].[roles_registration] ([role_registration_id], [role_registration_description], [is_active]) VALUES (1, N'CEO', 1)
INSERT INTO [dbo].[roles_registration] ([role_registration_id], [role_registration_description], [is_active]) VALUES (2, N'Chairman', 1)
INSERT INTO [dbo].[roles_registration] ([role_registration_id], [role_registration_description], [is_active]) VALUES (3, N'COO', 1)
INSERT INTO [dbo].[roles_registration] ([role_registration_id], [role_registration_description], [is_active]) VALUES (4, N'Managing Director', 1)
INSERT INTO [dbo].[roles_registration] ([role_registration_id], [role_registration_description], [is_active]) VALUES (5, N'Director', 1)
INSERT INTO [dbo].[roles_registration] ([role_registration_id], [role_registration_description], [is_active]) VALUES (6, N'Partner', 1)
INSERT INTO [dbo].[roles_registration] ([role_registration_id], [role_registration_description], [is_active]) VALUES (7, N'General Counsel', 1)
INSERT INTO [dbo].[roles_registration] ([role_registration_id], [role_registration_description], [is_active]) VALUES (8, N'Tax Head', 1)
INSERT INTO [dbo].[roles_registration] ([role_registration_id], [role_registration_description], [is_active]) VALUES (9, N'Tax Manager', 1)
INSERT INTO [dbo].[roles_registration] ([role_registration_id], [role_registration_description], [is_active]) VALUES (10, N'Finance manager', 1)
INSERT INTO [dbo].[roles_registration] ([role_registration_id], [role_registration_description], [is_active]) VALUES (11, N'Other', 1)
SET IDENTITY_INSERT [dbo].[roles_registration] OFF
