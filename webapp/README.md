# **ERP WebApp**

## **<span style="color:#FF">Requirements</span>**

| TECHNOLOGY | VERSION|
|---|---|
| Java | 21 |
| Apache Tomcat | 10.1 |
| Node | 20.7 |

**The application was successfully tested on these Operating Systems**
| OS | VERSION|
|---|---|
| MICROSOFT Windows | 11 |
| LINUX Fedora | *TBD* |

-----

## **Features**

### ***SSO Authentication***
* All users that use the System shall be identified.
* The System must authenticate user using SSO mechanism.
* List of allowed IDentity Provider:
  - Google

### ***Employee Profile***
* If a new user is registering, the System must generate a new **Employee Profile**.
* When an user is authenticated, the System must load the **Employee Profile**.
* The **Employee Profile** can be updated by the associated user.

### ***Company Registry***
* All existing **Companies** must be listed into a registry.
* An authenticated user can create a new **Company**.
* An authenticated user can leave a **Company**.
* An authenticated user can load a **Company** (if it's an **Employee** of the **Company**).

### ***Working Time***
* An authenticated user can impute work time.
* A **Work Time** must be loaded by an **Employee**.

+ Company 
  - Creator
  - Loader
+ Employee
  - Creator
  - Loader 