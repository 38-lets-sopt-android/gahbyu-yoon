package com.example.letssopt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letssopt.ui.theme.LETSSOPTTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var signUpEmail by remember { mutableStateOf("") }
    var signUpPassword by remember { mutableStateOf("") }
    var passwordcheck by remember { mutableStateOf("") }
    val context = LocalContext.current
    var currentContext = context
    while (currentContext is android.content.ContextWrapper && currentContext !is Activity) {
        currentContext = currentContext.baseContext
    }
    val activity = currentContext as? Activity


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
    )
    {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = "watcha",
                color = Color(0xFFE8003C),
                fontSize = 36.sp,
                fontWeight = FontWeight(700),
                modifier = Modifier.padding(top = 60.dp)
            )

            Text(
                text = "회원가입",
                fontSize = 20.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 20.dp, top = 30.dp)
            )

            Text(
                text = "이메일",
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF999999),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 20.dp, top = 60.dp)
            )

            TextField(
                value = signUpEmail,
                onValueChange = { signUpEmail = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(52.dp),
                label = { Text("이메일 주소를 입력하세요") },
                placeholder = { Text("이메일") },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF2A2A2A),
                    unfocusedContainerColor = Color(0xFF2A2A2A),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "비밀번호",
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF999999),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 20.dp)
            )

            TextField(
                value = signUpPassword,
                onValueChange = {
                    if (it.length <= 12) {
                        signUpPassword = it
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(52.dp),
                label = { Text("비밀번호를 입력하세요") },
                placeholder = { Text("비밀번호") },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF2A2A2A),
                    unfocusedContainerColor = Color(0xFF2A2A2A),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "비밀번호 확인",
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF999999),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 20.dp)
            )

            TextField(
                value = passwordcheck,
                onValueChange = {
                    if (it.length <= 12) {
                        passwordcheck = it
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(52.dp),
                label = { Text("비밀번호를 다시 입력하세요") },
                placeholder = { Text("비밀번호 재입력") },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF2A2A2A),
                    unfocusedContainerColor = Color(0xFF2A2A2A),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (signUpPassword.isNotEmpty() && signUpPassword == passwordcheck) {
                        val resultIntent = Intent()
                        resultIntent.putExtra("registered_email", signUpEmail)
                        resultIntent.putExtra("registered_password", signUpPassword)
                        activity?.setResult(Activity.RESULT_OK, resultIntent)
                        activity?.finish()
                    } else {
                        android.widget.Toast.makeText(
                            context,
                            "비밀번호가 일치하지 않습니다.",
                            android.widget.Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                enabled = signUpEmail.isNotBlank() && signUpPassword.isNotBlank() && passwordcheck.isNotBlank()
                        && android.util.Patterns.EMAIL_ADDRESS.matcher(signUpEmail).matches() &&
                        signUpPassword.length >= 8 && passwordcheck.length >= 8,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp)
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE8003C),
                    contentColor = Color(0xFFFFFFFF),
                    disabledContainerColor = Color(0xFF333333),
                    disabledContentColor = Color(0xFF666666)
                )
            )
            {
                Text(text = "회원가입")
            }
        }


    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    LETSSOPTTheme {
        Greeting2("Android")
    }
}