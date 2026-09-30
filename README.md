# 🌾 CropConnect

**CropConnect** is a full-stack agriculture management platform designed to centralize farmer, crop, farm-plot, fertilizer, harvest, market, subsidy, product, and warehouse information in a structured system.

The application combines a **React frontend** with a **Java Spring Boot REST backend** and a relational database using **JPA/Hibernate** for persistence.

The project is structured around a comprehensive agricultural domain model, making it suitable for managing relationships between farmers, their land, crops, production, storage, markets, and government subsidies.

---

# ✨ Features

## 👨‍🌾 Farmer Management

The platform provides functionality for managing farmer records.

Supported operations include:

* Add farmer
* View farmers
* Edit farmer information
* View farmer-related data
* Manage farmer relationships with farm plots and crops

Frontend:

```text
Farmer/
├── AddFarmer.jsx
├── EditFarmer.jsx
└── FarmerList.jsx
```

Backend:

```text
FarmerController
FarmerService
FarmerServiceImpl
FarmerRepository
Farmer
FarmerDTO
```

---

## 🌱 Crop Management

CropConnect allows agricultural crop information to be maintained through dedicated CRUD functionality.

```text
Crop/
├── AddCrop.jsx
├── EditCrop.jsx
└── CropList.jsx
```

The backend provides:

```text
CropController
CropService
CropServiceImpl
CropRepository
Crop
CropDTO
```

---

## 🚜 Farm Plot Management

Farm plots can be maintained separately from farmer information.

Features include:

* Add farm plots
* Edit farm plots
* List farm plots
* Associate plots with agricultural data

Frontend:

```text
FarmPlot/
├── AddFarmPlot.jsx
├── EditFarmPlot.jsx
└── FarmPlotList.jsx
```

Backend:

```text
FarmPlotController
FarmPlotService
FarmPlotServiceImpl
FarmPlotRepository
FarmPlot
FarmPlotDTO
```

---

## 🌾 Harvest Management

Harvest records can be created and managed through the application.

```text
Harvest/
├── AddHarvest.jsx
├── EditHarvest.jsx
└── HarvestList.jsx
```

The application also contains a dedicated harvest analytics interface:

```text
HarvestAnalytics.jsx
```

Backend components include:

```text
HarvestController
HarvestService
HarvestServiceImpl
HarvestRepository
Harvest
HarvestDTO
```

---

## 🧪 Fertilizer Management

Fertilizer information is managed through dedicated frontend and backend modules.

```text
FertilizerForm.jsx
FertilizerManagement.jsx
```

Backend:

```text
FertilizerController
FertilizerService
FertilizerServiceImpl
FertilizerRepository
Fertilizer
FertilizerDTO
```

---

## 🏪 Market Management

The system provides market-related management functionality.

```text
Market/
├── AddMarket.jsx
├── EditMarket.jsx
└── MarketList.jsx
```

Backend:

```text
MarketController
MarketService
MarketServiceImpl
MarketRepository
Market
MarketDTO
```

---

## 📦 Product Management

Products associated with the agricultural marketplace can be managed through:

```text
Product/
├── AddProduct.jsx
├── EditProduct.jsx
└── ProductList.jsx
```

Backend:

```text
ProductController
ProductService
ProductServiceImpl
ProductRepository
Product
ProductDTO
```

---

## 🏭 Warehouse & Inventory

CropConnect includes warehouse and inventory management functionality.

Frontend:

```text
Warehouse/
├── AddWarehouse.jsx
├── EditWarehouse.jsx
└── WarehouseList.jsx
```

Additional interfaces include:

```text
WarehouseInventory.jsx
```

Backend:

```text
WarehouseController
WarehouseService
WarehouseServiceImpl
WarehouseRepository
Warehouse
WarehouseDTO
```

---

## 💰 Subsidy Management

The application includes functionality for managing agricultural subsidies.

```text
Subsidy/
├── AddSubsidy.jsx
├── EditSubsidy.jsx
└── SubsidyList.jsx
```

Backend:

```text
SubsidyController
SubsidyService
SubsidyServiceImpl
SubsidyRepository
Subsidy
SubsidyDTO
```

The project also models subsidy applications through:

```text
SubsidyApplied
SubsidyAppliedId
SubsidyAppliedController
SubsidyAppliedService
```

---

# 🏗️ System Architecture

```text
                         CropConnect
                              │
                ┌─────────────┴─────────────┐
                │                           │
                ▼                           ▼
       React Frontend                 Spring Boot API
                │                           │
        ┌───────┴────────┐          ┌───────┴────────┐
        │                │          │                │
        ▼                ▼          ▼                ▼
     Dashboard       Management  Controllers       Services
                       Pages                         │
                                                    ▼
                                               Repositories
                                                    │
                                                    ▼
                                                 Database
```

---

# 🔄 Application Flow

A typical request follows:

```text
User
 │
 ▼
React Page / Form
 │
 ▼
Frontend Service
 │
 ▼
REST API
 │
 ▼
Spring Boot Controller
 │
 ▼
DTO
 │
 ▼
Service
 │
 ▼
Repository
 │
 ▼
JPA / Hibernate
 │
 ▼
Relational Database
```

---

# 🛠️ Technology Stack

## Frontend

* React
* JavaScript / JSX
* CSS
* Vite
* REST API integration

## Backend

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven
* REST APIs

## Database

* Relational database
* JPA/Hibernate ORM

## Development

* Git
* GitHub
* Maven Wrapper
* npm

---

# 📁 Project Structure

```text
CropConnect/
│
├── cropconnect-frontend/
│   │
│   ├── public/
│   │   ├── favicon.svg
│   │   └── icons.svg
│   │
│   ├── src/
│   │   ├── components/
│   │   │   ├── Card.jsx
│   │   │   ├── CropForm.jsx
│   │   │   ├── FarmerForm.jsx
│   │   │   ├── FarmPlotForm.jsx
│   │   │   ├── FertilizerForm.jsx
│   │   │   ├── Footer.jsx
│   │   │   ├── HarvestForm.jsx
│   │   │   ├── Loader.jsx
│   │   │   ├── Navbar.jsx
│   │   │   ├── Sidebar.jsx
│   │   │   └── Table.jsx
│   │   │
│   │   ├── layouts/
│   │   │   └── MainLayout.jsx
│   │   │
│   │   ├── pages/
│   │   │   ├── Crop/
│   │   │   │   ├── AddCrop.jsx
│   │   │   │   ├── EditCrop.jsx
│   │   │   │   └── CropList.jsx
│   │   │   │
│   │   │   ├── Farmer/
│   │   │   │   ├── AddFarmer.jsx
│   │   │   │   ├── EditFarmer.jsx
│   │   │   │   └── FarmerList.jsx
│   │   │   │
│   │   │   ├── FarmPlot/
│   │   │   │   ├── AddFarmPlot.jsx
│   │   │   │   ├── EditFarmPlot.jsx
│   │   │   │   └── FarmPlotList.jsx
│   │   │   │
│   │   │   ├── Harvest/
│   │   │   │   ├── AddHarvest.jsx
│   │   │   │   ├── EditHarvest.jsx
│   │   │   │   └── HarvestList.jsx
│   │   │   │
│   │   │   ├── Market/
│   │   │   │   ├── AddMarket.jsx
│   │   │   │   ├── EditMarket.jsx
│   │   │   │   └── MarketList.jsx
│   │   │   │
│   │   │   ├── Product/
│   │   │   │   ├── AddProduct.jsx
│   │   │   │   ├── EditProduct.jsx
│   │   │   │   └── ProductList.jsx
│   │   │   │
│   │   │   ├── Subsidy/
│   │   │   │   ├── AddSubsidy.jsx
│   │   │   │   ├── EditSubsidy.jsx
│   │   │   │   └── SubsidyList.jsx
│   │   │   │
│   │   │   ├── Warehouse/
│   │   │   │   ├── AddWarehouse.jsx
│   │   │   │   ├── EditWarehouse.jsx
│   │   │   │   └── WarehouseList.jsx
│   │   │   │
│   │   │   ├── Analytics.css
│   │   │   ├── CropManagement.jsx
│   │   │   ├── Dashboard.jsx
│   │   │   ├── FarmerDashboard.jsx
│   │   │   ├── FarmerProfile.jsx
│   │   │   ├── FarmPlotManagement.jsx
│   │   │   ├── FarmPlotManagementAdvanced.jsx
│   │   │   ├── FertilizerManagement.jsx
│   │   │   ├── HarvestAnalytics.jsx
│   │   │   ├── HarvestManagement.jsx
│   │   │   ├── Home.jsx
│   │   │   ├── MarketplaceHome.jsx
│   │   │   └── WarehouseInventory.jsx
│   │   │
│   │   ├── services/
│   │   │   ├── api.js
│   │   │   ├── cropService.js
│   │   │   ├── farmerService.js
│   │   │   ├── farmPlotService.js
│   │   │   ├── harvestService.js
│   │   │   ├── index.js
│   │   │   ├── marketService.js
│   │   │   ├── productService.js
│   │   │   ├── subsidyService.js
│   │   │   └── warehouseService.js
│   │   │
│   │   ├── App.jsx
│   │   ├── App.css
│   │   ├── index.css
│   │   └── main.jsx
│   │
│   ├── index.html
│   └── package.json
│
├── CropConnect_backend/
│   │
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/example/cropconnect/
│   │   │   │
│   │   │   │       ├── config/
│   │   │   │       │   └── CorsConfig.java
│   │   │   │       │
│   │   │   │       ├── controller/
│   │   │   │       │   ├── BelongsToController.java
│   │   │   │       │   ├── ControlledByController.java
│   │   │   │       │   ├── CropController.java
│   │   │   │       │   ├── FarmerController.java
│   │   │   │       │   ├── FarmPlotController.java
│   │   │   │       │   ├── FertilizerController.java
│   │   │   │       │   ├── GrowsController.java
│   │   │   │       │   ├── HarvestController.java
│   │   │   │       │   ├── MaintainsInventoryOfController.java
│   │   │   │       │   ├── MarketController.java
│   │   │   │       │   ├── ProductController.java
│   │   │   │       │   ├── SentToController.java
│   │   │   │       │   ├── SubsidyAppliedController.java
│   │   │   │       │   ├── SubsidyController.java
│   │   │   │       │   └── WarehouseController.java
│   │   │   │       │
│   │   │   │       ├── dto/
│   │   │   │       │   ├── ApiResponse.java
│   │   │   │       │   ├── CropDTO.java
│   │   │   │       │   ├── FarmerDTO.java
│   │   │   │       │   ├── FarmPlotDTO.java
│   │   │   │       │   ├── FertilizerDTO.java
│   │   │   │       │   ├── HarvestDTO.java
│   │   │   │       │   ├── MarketDTO.java
│   │   │   │       │   ├── ProductDTO.java
│   │   │   │       │   ├── SubsidyDTO.java
│   │   │   │       │   └── WarehouseDTO.java
│   │   │   │       │
│   │   │   │       ├── entity/
│   │   │   │       │   ├── Crop.java
│   │   │   │       │   ├── Farmer.java
│   │   │   │       │   ├── FarmPlot.java
│   │   │   │       │   ├── Fertilizer.java
│   │   │   │       │   ├── Harvest.java
│   │   │   │       │   ├── Market.java
│   │   │   │       │   ├── Product.java
│   │   │   │       │   ├── Subsidy.java
│   │   │   │       │   ├── Warehouse.java
│   │   │   │       │   │
│   │   │   │       │   ├── BelongsTo.java
│   │   │   │       │   ├── ControlledBy.java
│   │   │   │       │   ├── Grows.java
│   │   │   │       │   ├── MaintainsInventoryOf.java
│   │   │   │       │   ├── SentTo.java
│   │   │   │       │   └── SubsidyApplied.java
│   │   │   │       │
│   │   │   │       ├── repository/
│   │   │   │       │   ├── CropRepository.java
│   │   │   │       │   ├── FarmerRepository.java
│   │   │   │       │   ├── FarmPlotRepository.java
│   │   │   │       │   ├── FertilizerRepository.java
│   │   │   │       │   ├── HarvestRepository.java
│   │   │   │       │   ├── MarketRepository.java
│   │   │   │       │   ├── ProductRepository.java
│   │   │   │       │   ├── SubsidyRepository.java
│   │   │   │       │   └── WarehouseRepository.java
│   │   │   │       │
│   │   │   │       ├── service/
│   │   │   │       │   ├── CropService.java
│   │   │   │       │   ├── FarmerService.java
│   │   │   │       │   ├── FarmPlotService.java
│   │   │   │       │   ├── FertilizerService.java
│   │   │   │       │   ├── HarvestService.java
│   │   │   │       │   ├── MarketService.java
│   │   │   │       │   ├── ProductService.java
│   │   │   │       │   ├── SubsidyService.java
│   │   │   │       │   └── WarehouseService.java
│   │   │   │       │
│   │   │   │       └── service/impl/
│   │   │   │           ├── CropServiceImpl.java
│   │   │   │           ├── FarmerServiceImpl.java
│   │   │   │           ├── FarmPlotServiceImpl.java
│   │   │   │           ├── FertilizerServiceImpl.java
│   │   │   │           ├── HarvestServiceImpl.java
│   │   │   │           ├── MarketServiceImpl.java
│   │   │   │           ├── ProductServiceImpl.java
│   │   │   │           ├── SubsidyServiceImpl.java
│   │   │   │           └── WarehouseServiceImpl.java
│   │   │   │
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   │
│   │   └── test/
│   │       └── java/
│   │           └── com/example/cropconnect/
│   │               └── CropconnectApplicationTests.java
│   │
│   ├── .mvn/
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
│
└── README.md
```

---

# 🗃️ Database / Domain Model

One of the main characteristics of CropConnect is its relatively large relational domain.

The major entities are:

```text
Farmer
   │
   └── FarmPlot
          │
          └── Crop
                │
                └── Harvest
```

Additional agricultural and supply-chain entities include:

```text
Fertilizer
Market
Product
Warehouse
Subsidy
```

The project also models relationships between these entities using dedicated relationship classes.

---

# 🔗 Entity Relationships

The backend contains explicit relationship entities such as:

```text
BelongsTo
ControlledBy
Grows
MaintainsInventoryOf
SentTo
SubsidyApplied
```

For example, conceptually:

```text
                 Farmer
                   │
                   │
               owns/manages
                   │
                   ▼
               FarmPlot
                   │
                   │ grows
                   ▼
                  Crop
                   │
                   │ produces
                   ▼
                Harvest
```

Other relationships connect agricultural products with markets and warehouses:

```text
Crop / Product
      │
      ├──────────► Market
      │
      └──────────► Warehouse
                       │
                       └── Inventory
```

Subsidy relationships are modeled separately:

```text
Farmer
   │
   │ applies for
   ▼
Subsidy
```

The exact relationship semantics are defined by the JPA entity mappings in the backend.

---

# 🧱 Backend Architecture

CropConnect follows a layered Spring Boot architecture:

```text
                   REST Request
                        │
                        ▼
                   Controller
                        │
                        ▼
                      DTO
                        │
                        ▼
                     Service
                        │
                        ▼
                   Repository
                        │
                        ▼
                  JPA Entity
                        │
                        ▼
                    Database
```

### Controllers

Controllers expose REST endpoints for individual domain modules.

Examples:

```text
CropController
FarmerController
FarmPlotController
HarvestController
MarketController
ProductController
SubsidyController
WarehouseController
```

Relationship-specific controllers are also present:

```text
BelongsToController
ControlledByController
GrowsController
MaintainsInventoryOfController
SentToController
SubsidyAppliedController
```

---

# 🧠 Service Layer

Business logic is separated into service interfaces and implementations.

Example:

```text
CropService
     │
     ▼
CropServiceImpl
```

The same structure is used for other modules:

```text
FarmerService
FarmPlotService
FertilizerService
HarvestService
MarketService
ProductService
SubsidyService
WarehouseService
```

This separation keeps business logic independent from controllers and persistence.

---

# 🗄️ Repository Layer

Each major domain entity has a corresponding repository.

For example:

```text
CropRepository
FarmerRepository
FarmPlotRepository
HarvestRepository
MarketRepository
ProductRepository
SubsidyRepository
WarehouseRepository
```

Spring Data JPA handles persistence operations through these repository interfaces.

---

# 📦 DTO Layer

The project uses Data Transfer Objects to separate API representations from persistence entities.

Examples:

```text
CropDTO
FarmerDTO
FarmPlotDTO
FertilizerDTO
HarvestDTO
MarketDTO
ProductDTO
SubsidyDTO
WarehouseDTO
```

A generic response wrapper is also provided:

```text
ApiResponse
```

---

# 🌐 Frontend Architecture

The React application separates reusable components, pages, layouts, and API services.

```text
                     React Application
                            │
             ┌──────────────┼──────────────┐
             │
```
