# RecyclerView Generic Adapter Library

This sample application gives an example of using a generic RecylerView adapter which can be used by multiple RecylerViews from different fragments. 

This helps in reducing boiler-plate code and removing all the business logic out of the RV adapter and pushing it into the ViewModel.
[![](https://jitpack.io/v/amitrawat8/RecyclerViewGenericAdapterLibrary.svg)](https://jitpack.io/#amitrawat8/RecyclerViewGenericAdapterLibrary)

#  Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
#  Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.amitrawat8:RecyclerViewGenericAdapterLibrary:0.1.0'
	}
