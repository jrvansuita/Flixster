plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
}

android {
	namespace = "com.vansuita.flixster"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.vansuita.flixster"
		minSdk = 24
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		debug {
			isDebuggable = true


			buildConfigField("String", "COVER_BASE_URL", "\"https://image.tmdb.org/t/p/w500/\"")
			buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3\"")
			buildConfigField(
				"String",
				"API_TOKEN",
				"\"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5ZTNiOTNmYTM5MjNhNDEwYTY5NTUyMWVkM2Y4YTk0OCIsInN1YiI6IjY1YjI1ZmU5YjdiNjlkMDBjOWYzZmQzMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ._e58Ne7WkjfFhNFr3_vPnfhZpTxdBgNDdUTjf9rI6QQ\""
			)
		}
		release {
			isMinifyEnabled = false

			buildConfigField("String", "COVER_BASE_URL", "\"https://image.tmdb.org/t/p/w500/\"")
			buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3\"")
			buildConfigField(
				"String",
				"API_TOKEN",
				"\"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5ZTNiOTNmYTM5MjNhNDEwYTY5NTUyMWVkM2Y4YTk0OCIsInN1YiI6IjY1YjI1ZmU5YjdiNjlkMDBjOWYzZmQzMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ._e58Ne7WkjfFhNFr3_vPnfhZpTxdBgNDdUTjf9rI6QQ\""
			)

			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
	buildFeatures {
		viewBinding = true
		buildConfig = true
	}
}

dependencies {
	implementation("com.codepath.libraries:asynchttpclient:2.2.0")
	implementation("com.google.code.gson:gson:2.10.1")
	api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
	api("androidx.activity:activity-ktx:1.8.2")
	implementation("com.github.bumptech.glide:glide:4.16.0")
	implementation("androidx.core:core-ktx:1.12.0")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("com.google.android.material:material:1.11.0")
	implementation("androidx.constraintlayout:constraintlayout:2.1.4")
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}