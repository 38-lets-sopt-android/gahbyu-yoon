package com.example.letssopt

import android.app.Activity
import android.widget.Toast
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.ui.theme.LETSSOPTTheme


@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = viewModel(),
    onSignUpSuccess: () -> Unit,
) {
    val signUpEmail by viewModel.email
    val signUpPassword by viewModel.password
    val passwordcheck by viewModel.passwordCheck
    val context = LocalContext.current
    val activity = context as? Activity

    LaunchedEffect(Unit) {
        viewModel.signUpEvent.collect { event ->
            when (event) {
                is SignUpEvent.SignUpSuccess -> {
                    onSignUpSuccess()
                }
                is SignUpEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

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
                onValueChange = { viewModel.updateEmail(it) },
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
                        viewModel.updatePassword(it)
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
                        viewModel.updatePasswordCheck(it)
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
                    viewModel.signUp()
                    onSignUpSuccess()

                },
                enabled = viewModel.isSignUpValid(),
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
fun SignUpActivityPreview() {
    LETSSOPTTheme {
    }
}