package com.example.letssopt

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.ui.theme.ContainerColor
import com.example.letssopt.ui.theme.LETSSOPTTheme


@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = viewModel(),
    onNavigateToSignUp: () -> Unit,
    onSignInSuccess: () -> Unit,
    prefManager: PreferenceManager
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    var loginId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    var showDialog by remember { mutableStateOf(false) }


    LaunchedEffect(uiState) {
        if (uiState is SignInUiState.Success) {
            onSignInSuccess()
            viewModel.resetState()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff141414))
            .padding(horizontal = 24.dp),
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
            text = "아이디로 로그인",
            fontSize = 20.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFFFFFFFF),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp, top = 30.dp)
        )

        Text(
            text = "아이디",
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF999999),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp, top = 60.dp)
        )

        OutlinedTextField(
            value = loginId,
            onValueChange = { loginId = it },
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
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(62.dp),
            label = { Text("비밀번호를 입력하세요") },
            placeholder = { Text("비밀번호를 입력하세요") },
            singleLine = true,
            visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = ContainerColor,
                unfocusedContainerColor = ContainerColor,
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
                    onNavigateToSignUp()
                }
                .padding(bottom = 10.dp)
        )


        if (uiState is SignInUiState.Loading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = {
                    viewModel.signIn(
                        loginId,
                        password,
                    )
                },
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
                enabled = loginId.isNotBlank() && password.isNotBlank()
            ) {
                Text("회원가입")
            }
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


@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LETSSOPTTheme {

    }
}