# Quizinator

Generating quizzes, on Android, from YouTube videos.

# How to use

### Getting the questions and answers

1. Find a YouTube video you want to generate a quiz from and copy the URL.
2. Import the Colab notebook `Quizinator.ipynb` into Google Colab.
3. You will need a Gemini API key to run the notebook. You can get one [here](https://ai.google.dev/gemini-api/docs/api-key).
4. Replace the `YOUR_API_KEY` with your Gemini API key.
5. Now just run the notebook and you will get the questions and answers saved in a JSON file.

### Using the app

#### Installing the app
You can download the APK from the [releases]() page. You can the install the APK on your Android device.

#### Building the app
You can also build the Application yourself in Android Studio. Just clone the repository and open the project in Android Studio. You can then build the APK and install it on your Android device or emulator.

After you can run the app and select the JSON file you got from the Colab notebook. The app will then generate a quiz from the questions and answers in the JSON file.