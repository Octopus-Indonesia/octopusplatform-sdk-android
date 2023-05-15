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

#### Example using TimePicker

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


#### Example using TextPicker

```kotlin
import id.co.octopus.library.core.DialogUtils
import id.co.octopus.library.core.textpicker.TextPickerListener
import id.co.octopus.library.core.textpicker.TextPickerView

class MainActivity : AppCompatActivity(), TextPickerListener //implement the listener
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
	
	//init the view
	val textPickerView = findViewById<TextPickerView>(R.id.textPickerView)
	
	//set list to component
	val list = listOf("Item 1", "Item 2", "Item 3 Item 3 Item 3 Item 3 Item 3 Item 3 Item 3 Item 3")
        textPickerView.setCustomList(list)
	
	button.setOnClickListener {
		Log.d(TAG, "${textPickerView.getTextPicked()}")
        }
    }

    /*
    * Get text who selected in bottom dialog
    * */
    override fun onTextPicked(textSelected: String) {
        Toast.makeText(
            this,
            "textSelected: $textSelected",
            Toast.LENGTH_SHORT
        ).show()
    }
}
```

### XML

If you need to add Material Date/Time picker for the layout. All you have to do is :

```XML
<id.co.octopus.library.core.textpicker.TextPickerView
        android:id="@+id/textPickerView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="32dp"
        android:layout_marginStart="16dp"
        android:layout_marginEnd="16dp"
        app:textGravityOfPicker="center"
        app:textSizePickerDefault="18sp"
        app:textSizePickerSelected="20sp"
        app:backgroundCenterOfView="@drawable/bg_green"
        />
```
## Support custom attibutes

### Common
|Attribute|Format|Default|
|---|---|---|
|`backgroundCenterOfView`|reference|drawable|
|`textColorPickerSelected`|color|#151515|
|`textColorPickerDefault`|color|#AEAEAE|
|`textSizePickerSelected`|dimension|20sp|
|`textSizePickerDefault`|dimension|18sp|
|`textGravityOfPicker`|integer|start/center/end|

#### Example to show TextPicker dialog

```kotlin
import id.co.octopus.library.core.DialogUtils
import id.co.octopus.library.core.textpicker.TextPickerListener
import id.co.octopus.library.core.textpicker.TextPickerView

class MainActivity : AppCompatActivity(), TextPickerListener //implement the listener
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
	
	button.setOnClickListener {
		DialogUtils.showTextPickerBottomDialog(
			context = this@MainActivity,
			dialogTitle = "Choose Gender",
			list = list,
			listener = this@MainActivity,
			textSizePickerSelected = resources.getDimension(R.dimen.text_picker_view_selected_size), //default is 20sp
			textSizePickerDefault = resources.getDimension(R.dimen.text_picker_view_default_size) //default is 18sp
			textColorPickerSelected = Color.GREEN, //default is #151515
			textColorPickerDefault = resources.getColor(R.color.purple_200) //default is #AEAEAE
		)
        }
    }

    /*
    * Get text who selected in bottom dialog
    * */
    override fun onTextPicked(textSelected: String) {
        Toast.makeText(
            this,
            "textSelected: $textSelected",
            Toast.LENGTH_SHORT
        ).show()
    }
}
```

#### Demo

[device-2023-05-15-111231.webm](https://github.com/Octopus-Indonesia/octopusplatform-sdk-android/assets/14218447/fc8a3f14-1a10-4e40-9f8e-cab16b0b61a5)
