# CryptoCurrencyMarketAnalysis
Sample project for analyzing Crypto currency market


As part of implementation, I have created independent layers for UI and Service[logic]. Both of these layers can be handled independently.

Any change can be made in these modules easily without affecting other modules.
Test classes and methods are built in parallel to development to ensure code stability.
Test cases and data are prepared prior to implementation


Technologies Used 

	React and Node Js for UI module which can be deployed in a container 
	Springboot Micro service architecture in service layer which can also be deployed in a container
	Mongo DB as Repository
	Test Driven approach by integrating Junit test case classes


UI Module
•	Front end components are built using React and it is a Single page application
•	NodeJS is acting as the backend service provider for UI Module
•	Used Native HTML tags embedded with react component (jsx) 
•	 implemented Ajax calls to communicate with service layer
•	This module can be scaled/changed separately without affecting other layers

Service Module
      
•	Service layer is built using spring boot micro service architecture
•	Currently, it can be executed as a standalone Rest service 
•	Rest APIs have been implemented to receive inputs from UI
•	Mongo DB driver has been used to communicate with Database
•	This can be extended to proper MS architecture using Service registry, gateway and load balancer / using kubernetes.
DB Layer

•	Mongo DB is the repository that used in this approach
•	 It stores data in json format and relieves the same from service layer using driver integration
•	All details are entered in DB prior to application Testing




