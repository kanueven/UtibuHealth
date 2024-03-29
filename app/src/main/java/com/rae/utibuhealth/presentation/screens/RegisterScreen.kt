package com.rae.utibuhealth.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.rae.utibuhealth.R
import com.rae.utibuhealth.presentation.components.MyHeaderText
import com.rae.utibuhealth.presentation.components.MyPasswordTextField
import com.rae.utibuhealth.presentation.components.MyTextField
import com.rae.utibuhealth.presentation.viewmodel.LoginSignupViewModel
import com.rae.utibuhealth.ui.theme.Primary
import com.rae.utibuhealth.ui.theme.Secondary

@Composable
fun RegisterScreen(
    viewModel: LoginSignupViewModel,
    onSignup: () -> Unit
){
    val emailReg = viewModel.emailReg
    val passReg = viewModel.passwordReg
    val firstName = viewModel.firstName
    val lastName = viewModel.lastName
    val confirmpass = viewModel.confrimpass
    val isRegEnabled = viewModel.isRegisterEnabled()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(defaulpadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MyHeaderText(
            text = stringResource(id = R.string.sign_up),
            modifier = Modifier
                .padding(vertical = defaulpadding)
                .align(Alignment.CenterHorizontally)
        )
        MyTextField(
            value = firstName,
            onValueChange = { viewModel.onFirstName(it)},
            leadingIcon = Icons.Default.Person,
            labelValue = stringResource(id = R.string.first_name))
        Spacer(modifier = Modifier.height(defaulpadding))
        MyTextField(
            value = lastName,
            onValueChange = { viewModel.onLastName(it)},
            labelValue = stringResource(id = R.string.last_name),
            leadingIcon = Icons.Default.Person)
        Spacer(modifier = Modifier.height(defaulpadding))
        MyTextField(
            value = emailReg,
            onValueChange = { viewModel.onEmailRegChange(it)},
            labelValue = stringResource(id = R.string.email),
            leadingIcon = Icons.Default.Email)
        Spacer(modifier = Modifier.height(defaulpadding))
        MyPasswordTextField(
            value = passReg,
            onValueChange = { viewModel.onPasswordRegChange(it)},
            labelValue = stringResource(id = R.string.password),
            leadingIcon = Icons.Default.Lock,
            keyboardType = KeyboardType.Password,
            trailingIcon = { })
        Spacer(modifier = Modifier.height(defaulpadding))
        MyPasswordTextField(
            value = confirmpass,
            onValueChange = { viewModel.onConfirmPassword(it)},
            labelValue = stringResource(id = R.string.confirm_pass),
            leadingIcon = Icons.Default.Lock,
            keyboardType = KeyboardType.Password,
            trailingIcon = { /*TODO*/ })
        Spacer(modifier = Modifier.height(defaulpadding))
        Button(
            onClick = { onSignup},
            modifier = Modifier.fillMaxWidth(),
            enabled = !isRegEnabled,
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(androidx.compose.ui.graphics.Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(48.dp)
                    .background(
                        brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                        shape = RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center,


                ) {
                Text(text = stringResource(id = R.string.sign_up))

            }

        }

    }
}