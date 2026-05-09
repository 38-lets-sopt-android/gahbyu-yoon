package com.example.letssopt

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.letssopt.ui.theme.ContainerColor
import com.example.letssopt.ui.theme.LETSSOPTTheme

private val partOptions = listOf("ios", "안드로이드", "웹")

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = viewModel(),
    onSignUpSuccess: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    val loginId by viewModel.loginId.collectAsState()
    val password by viewModel.password.collectAsState()
    val passwordCheck by viewModel.passwordCheck.collectAsState()
    val name by viewModel.name.collectAsState()
    val email by viewModel.email.collectAsState()
    val age by viewModel.age.collectAsState()
    val part by viewModel.part.collectAsState()

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    LaunchedEffect(uiState) {
        when (val state = uiState) {
            is SignUpUiState.Success -> {
                onSignUpSuccess()
                viewModel.resetState()
            }
            is SignUpUiState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                viewModel.resetState()
            }
            else -> {}
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff141414))
            .padding(horizontal = 24.dp)
            .verticalScroll(scrollState),
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
                .padding(start = 20.dp, top = 60.dp)
        )

        Text(
            text = "아이디",
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF999999),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp, top = 30.dp)
        )

        OutlinedTextField(
            value = loginId,
            onValueChange = { viewModel.updateLoginId(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(62.dp),
            label = { Text("아이디를 입력하세요") },
            placeholder = { Text("아이디를 입력하세요") },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = ContainerColor,
                unfocusedContainerColor = ContainerColor,
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

        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.updatePassword(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(62.dp),
            label = { Text("비밀번호를 입력하세요") },
            placeholder = { Text("비밀번호를 입력하세요") },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = ContainerColor,
                unfocusedContainerColor = ContainerColor,
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

        OutlinedTextField(
            value = passwordCheck,
            onValueChange = { viewModel.updatePasswordCheck(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(62.dp),
            label = { Text("비밀번호를 다시 입력하세요") },
            placeholder = { Text("비밀번호를 재입력하세요") },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = ContainerColor,
                unfocusedContainerColor = ContainerColor,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "이름",
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF999999),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { viewModel.updateName(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(62.dp),
            label = { Text("이름을 입력하세요") },
            placeholder = { Text("이름을 입력하세요") },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = ContainerColor,
                unfocusedContainerColor = ContainerColor,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "이메일",
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF999999),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.updateEmail(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(62.dp),
            label = { Text("이메일을 입력하세요") },
            placeholder = { Text("이메일을 입력하세요") },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = ContainerColor,
                unfocusedContainerColor = ContainerColor,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "나이",
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF999999),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp)
        )

        OutlinedTextField(
            value = age,
            onValueChange = { viewModel.updateAge(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(62.dp),
            label = { Text("나이를 입력하세요") },
            placeholder = { Text("나이를 입력하세요") },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = ContainerColor,
                unfocusedContainerColor = ContainerColor,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "파트",
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF999999),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp)
        )

        OutlinedTextField(
            value = part,
            onValueChange = { viewModel.updatePart(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(62.dp),
            label = { Text("파트 (iOS / 안드로이드 / 웹)") },
            placeholder = { Text("파트를 입력하세요") },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = ContainerColor,
                unfocusedContainerColor = ContainerColor,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(40.dp))


        if (uiState is SignUpUiState.Loading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = { viewModel.signUp() },
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
                ),
                enabled = viewModel.isSignUpValid()
            ) {
                Text("회원가입")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpActivityPreview() {
    LETSSOPTTheme {
        SignUpScreen(onSignUpSuccess = {})
    }
}