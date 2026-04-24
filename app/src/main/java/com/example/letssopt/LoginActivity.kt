package com.example.letssopt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.window.Dialog
import com.example.letssopt.ui.theme.LETSSOPTTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefManager = PreferenceManager(this)
        if (prefManager.isLoggedIn()) {
            val intent = Intent(this, ScreenActivity::class.java)
            startActivity(intent)
            finish()
            return
        }
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var registeredEmail by remember { mutableStateOf("") }
    var registeredPassword by remember { mutableStateOf("") }
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    val signUpLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            registeredEmail = data?.getStringExtra("registered_email") ?: ""
            registeredPassword = data?.getStringExtra("registered_password") ?: ""
            Toast.makeText(context, "회원가입 완료! 로그인 해주세요.", Toast.LENGTH_SHORT).show()
        }
    }
    val prefManager = remember { PreferenceManager(context) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
    ) {

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
                text = "이메일로 로그인",
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
                value = email,
                onValueChange = { email = it },
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
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(52.dp),
                label = { Text("비밀번호를 입력하세요") },
                placeholder = { Text("비밀번호") },
                singleLine = true,
                visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF2A2A2A),
                    unfocusedContainerColor = Color(0xFF2A2A2A),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "아직 계정이 없으신가요? 회원가입",
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF999999),
                modifier = Modifier
                    .clickable {
                        val intent = Intent(context, SignUpActivity::class.java)
                        signUpLauncher.launch(intent)

                    }
                    .padding(bottom = 10.dp)
            )


            Button(
                onClick = {
                    if (email.isNotEmpty() && email == registeredEmail && password == registeredPassword) {
                        prefManager.setLoggedIn(true)
                        val intent = Intent(context, ScreenActivity::class.java)
                        context.startActivity(intent)
                        (context as? Activity)?.finish()
                    } else {
                        Toast.makeText(context, "이메일 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
                enabled = email.isNotBlank() && password.isNotBlank(),
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
            ) {
                Text(text = "로그인")
            }
        }

        if (showDialog) {
            Dialog(
                onDismissRequest = { showDialog = false }
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("로그인 되었습니다!", modifier = Modifier.padding(bottom = 16.dp))
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text("확인")
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LETSSOPTTheme {
        Greeting("Android")
    }
}