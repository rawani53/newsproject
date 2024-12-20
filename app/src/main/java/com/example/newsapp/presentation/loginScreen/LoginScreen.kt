package com.example.newsapp.presentation.loginScreen

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsapp.navigation.HomePageScreen
import com.example.newsapp.presentation.homePage.viewModel.NewsViewModel
import com.example.newsapp.presentation.loginScreen.components.BackGroundGradient
import com.example.newsapp.R
import com.example.newsapp.utils.GoogleSignInUtils

@Composable
fun LoginScreen(viewModel: NewsViewModel, navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    //Launcher Setup
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            GoogleSignInUtils.doGoogleSignIn(
                context = context,
                scope = scope,
                launcher = null,
                logintoast = {
                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                },
                login = { navController.navigate(HomePageScreen) }
            )
        }

    // UI
    Box {
        Image(
            painter = painterResource(R.drawable.newwallbg),
            contentDescription = "bg",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        BackGroundGradient(modifier = Modifier)

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.loginsceenlogo),
                contentDescription = "login image",
                modifier = Modifier.size(150.dp)
            )
            Text(
                "NewsNation", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Login to your account", color = Color.DarkGray)
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email address", color = Color.DarkGray) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.DarkGray) },
                visualTransformation = PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(Color.DarkGray)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                viewModel.fetchNewsTopHeadlines()
                GoogleSignInUtils.doGoogleSignIn(
                    context = context,
                    scope = scope,
                    launcher = launcher,
                    logintoast = {
                        Toast.makeText(context, "Login successful", Toast.LENGTH_LONG).show()
                    },
                    login = { navController.navigate(HomePageScreen) }
                )
            }){
                Text("Login")
            }

            Spacer(modifier = Modifier.height(11.dp))

            TextButton(onClick = {}) {
                Text(
                    "Forgot Password?", color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text("Or sign in with")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "facebook",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable {})
                Image(painter = painterResource(id = R.drawable.google),
                    contentDescription = "google",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable {
                            GoogleSignInUtils.doGoogleSignIn(
                                context = context,
                                scope = scope,
                                launcher = launcher,
                                logintoast = {
                                    Toast
                                        .makeText(context, "Login successful", Toast.LENGTH_SHORT)
                                        .show()
                                },
                                login = { navController.navigate(HomePageScreen) }
                            )
                            viewModel.fetchNewsTopHeadlines()
                        })

                Image(painter = painterResource(id = R.drawable.twitter),
                    contentDescription = "twitter",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable {})

            }

        }
    }
}

