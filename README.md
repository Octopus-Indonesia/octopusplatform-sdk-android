# octopusplatform-sdk-android
This library is a collection of components and utilities that will be used in other Octopus Android projects

## To Install

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```java
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Step 2. Add the dependency
```java
  dependencies {
	     implementation 'com.github.Octopus-Indonesia:octopusplatform-sdk-android:{latest-version}'
	}
```

#### Usage TimePickerDialog

Simply implement `TimePickerListener` in your activity, call the method and show it

#### Example

```kotlin
import id.co.octopus.library.core.DialogUtils
import id.co.octopus.library.core.timepicker.TimePickerListener

class MainActivity : AppCompatActivity(), TimePickerListener {

    private fun showDialog() {
        DialogUtils.showTimePickerBottomDialog(
		this@MainActivity, 
		"Title Dialog",
		textView.text.toString(),
		this@MainActivity
	)
    }

    /*
    * Get time who picked in time picker dialog
    * */
    override fun onPicked(timePicked: String) {
        Log.d(TAG, timePicked)
	textView.text = timePicked
    }
}
```

#### Demo

https://user-images.githubusercontent.com/14218447/172750271-c9e50e60-9046-4703-ab95-8fbe47bc8ad8.mp4




