
# Welcome to Keyboard Widget!  
  
[![](https://jitpack.io/v/thushcapone/keyboard_view.svg)](https://jitpack.io/#thushcapone/keyboard_view)   
  
KeyboardView is your visual keyboard that is Android Architecture Component friendly, compatible with Data-Binding and all InputText.
  
### Screenshots  
<img src="https://raw.githubusercontent.com/thushcapone/keyboard_view/master/screenshots/demo-keyboard-unshuffled.png" width="250">&nbsp;&nbsp;<img src="https://raw.githubusercontent.com/thushcapone/keyboard_view/master/screenshots/demo-keyboard-shuffled.png" width="250">  
  
  
## Adding to project  
  
### Gradle  
Add below code to your **root** `build.gradle` file (if you have multiple modules and only one of them require `KKeyboardView`, add the `jitpack` url only in that module's `build.gradle`).  
```groovy  
allprojects {  
 repositories { 
	 maven { 
		 url 'https://jitpack.io' 
		 } 
	 }
}  
```  
And add the following dependency code to your **module**'s `build.gradle` file.  
```groovy  
dependencies {  
 implementation "com.github.thushcapone:keyboard_view:${latest-version}"
}  
```  
  
## Usage  
```xml  
<com.google.android.material.textfield.TextInputEditText
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginLeft="@dimen/spacing_large"
    android:layout_marginRight="@dimen/spacing_large"
    android:gravity="center"
    android:inputType="number"
    android:textSize="@dimen/font_large"
    app:keyboard="@{keyboard}"
    />

<com.thushcapone.keyboard_view.KeyboardView
    android:id="@+id/keyboard"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1"
    android:layout_marginLeft="@dimen/spacing_huge"
    android:layout_marginRight="@dimen/spacing_huge"
    android:gravity="bottom"
    app:shuffle="true"
    app:keysColor="@color/colorPrimary"
    />

```  

## Usage with MVVM + DataBinding  
```xml  
<com.google.android.material.textfield.TextInputEditText
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginLeft="@dimen/spacing_large"
    android:layout_marginRight="@dimen/spacing_large"
    android:gravity="center"
    android:inputType="number"
    android:textSize="@dimen/font_large"
    app:keyboard="@{keyboard}"
    />

<com.thushcapone.keyboard_view.KeyboardView
    android:id="@+id/keyboard"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1"
    android:layout_marginLeft="@dimen/spacing_huge"
    android:layout_marginRight="@dimen/spacing_huge"
    android:gravity="bottom"
    app:shuffle="true"
    app:keysColor="@color/colorPrimary"
    bind:validateAction="@{viewModel::onValidOtpTyped}"
    />
```  
  
### Customization  
  
Table below describes the properties available to customize the KeyboardView.  
  
  
| Property Name          | Format    | Description |  
|------------------------|-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|  
| shuffle            | boolean     | Set if the keyboard keys are shuffled or not                              |  
| keyboard            | reference | Links the keyboard to the editText |  
| keysColor           | reference | Set the color of the keys based on the `color resource` |  
| validateAction                | binding-function | Will be called when the enter button is hit |

# License  
```  
Copyright (c) 2019. T.C.  
  
Licensed under the Apache License, Version 2.0 (the "License");  
you may not use this file except in compliance with the License.  
You may obtain a copy of the License at  
  
 http://www.apache.org/licenses/LICENSE-2.0  
Unless required by applicable law or agreed to in writing, software  
distributed under the License is distributed on an "AS IS" BASIS,  
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
See the License for the specific language governing permissions and  
limitations under the License. ```