#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Customers

Background: Steps common for all scenarios
  	Given User Launch Chrome browser
  	When User opens URL "https://admin-demo.nopcommerce.com/login"
		And User enters Email as "admin@yourstore.com" and Password as "admin"
		And Click on Login
		Then User can view Dashboard
  
  @regression
  Scenario: Add New Customer
   
		When User click on Customer Menu
		And Click on Customers Menu Item
		And Click on Add new button
		Then User can view add new Customer Page
		When User enter customer info
		And Click on save buttton
		Then User can view confirmation message "The new customer has been added successfully."
		And close browser
	
	@regression	
	Scenario: Search Customer By Email
	
		When User click on Customer Menu
		And Click on Customers Menu Item
		And Enter Customer Email
		When Click On Search button
		Then User should found email in Search Table
		And close browser
	
	@sanity	
	Scenario: Search Customer By Name
	
		When User click on Customer Menu
		And Click on Customers Menu Item
		And Enter Customer FirstName
		And Enter Customer LastName
		When Click On Search button
		Then User should found Name in Search Table
		And close browser
		
		