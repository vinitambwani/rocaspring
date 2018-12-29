CREATE TABLE [dbo].transaction_subscribtion_Details
(
	[transaction_subscribtion_id] INT NOT NULL PRIMARY KEY, 
    [subscription_id] INT NOT NULL, 
    [subscription_details_id] INT NULL, 
    [is_Active] INT NULL, 
    CONSTRAINT [FK_transaction_subscribtion_Details_ToTable] FOREIGN KEY (subscription_id) REFERENCES [subscription](subscription_id)
)
