# Android-with-Kotlin
Kotlin code example for android projects.

## Compose example as list
![Screenshot_1627898028](https://user-images.githubusercontent.com/14831652/127842919-cca906dc-b2f5-4452-a26f-d467b5a06d38.png)

-
## Activity life cycle
### Navigation between two activity
Main Activity-> click on second activity button->

dpm.android.with.kotlin D/MainActivity: onCreate...

dpm.android.with.kotlin D/MainActivity: onStart...

dpm.android.with.kotlin D/MainActivity: onResume...

Press go to next activity

dpm.android.with.kotlin D/MainActivity: onPause...

dpm.android.with.kotlin D/SecondActivity: onCreate

dpm.android.with.kotlin D/SecondActivity: onStart

dpm.android.with.kotlin D/SecondActivity: onResume

dpm.android.with.kotlin D/MainActivity: onStop...

dpm.android.with.kotlin D/SecondActivity: onPause

dpm.android.with.kotlin D/MainActivity: onRestart...

dpm.android.with.kotlin D/MainActivity: onStart...

dpm.android.with.kotlin D/MainActivity: onResume...

dpm.android.with.kotlin D/SecondActivity: onStop

dpm.android.with.kotlin D/SecondActivity: onDestroy


### Single Activity press home button

Start Main activity->

/dpm.android.with.kotlin D/MainActivity: onCreate...

/dpm.android.with.kotlin D/MainActivity: onStart...

/dpm.android.with.kotlin D/MainActivity: onResume...

Press home button->

/dpm.android.with.kotlin D/MainActivity: onPause...

/dpm.android.with.kotlin D/MainActivity: onStop...

/dpm.android.with.kotlin D/MainActivity: onRestart...

/dpm.android.with.kotlin D/MainActivity: onStart...

/dpm.android.with.kotlin D/MainActivity: onResume...
