package com.rae.utibuhealth.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rae.utibuhealth.R
import com.rae.utibuhealth.presentation.components.ButtonComponent
import com.rae.utibuhealth.presentation.components.MyHeaderText
import com.rae.utibuhealth.presentation.components.MyPasswordTextField
import com.rae.utibuhealth.presentation.components.MyTextField
import com.rae.utibuhealth.presentation.viewmodel.LoginSignupViewModel

val defaulpadding = 12.dp
@Composable
fun LoginScreen(
    viewModel: LoginSignupViewModel,
    onLoginClick: () -> Unit,
    onSignUpClick:  () -> Unit
){


    val email = viewModel.email
    val password = viewModel.password
    val isLoginEnabled = viewModel.isEnabledLog()
    val checked = viewModel.checked


    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(){
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.40f),
                painter = painterResource(id = R.drawable.shape),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }
        MyHeaderText(
            text = stringResource(id = R.string.log_in),
            modifier = Modifier.padding(defaulpadding)
            )
        MyTextField(
            value = email,
            onValueChange = {  viewModel.onEmailChange(it)},
            labelValue = stringResource(id = R.string.username),
            leadingIcon = Icons.Default.Person,
        )
        Spacer(modifier = Modifier.height(8.dp))
        MyPasswordTextField(
            value = password,
            onValueChange = { viewModel.onPasswordChange(it) } ,
            labelValue = stringResource(id = R.string.password),
            leadingIcon = Icons.Default.Lock,
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password,
            trailingIcon = {}
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = checked
                    , onCheckedChange = { viewModel.onCheckedChange(it) })
                Text(text = stringResource(id = R.string.remember))
            }
            TextButton(onClick = {}) {
                Text(text = stringResource(id = R.string.forgot))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        ButtonComponent(
            value = stringResource(id = R.string.log_in)
            , onClick ={onLoginClick} ,
            isFieldEmpty = !isLoginEnabled )

        AlternativeLoginOptions(
            onIconClick = { index ->
                when(index){
                    0->{
                        Toast.makeText(context, "Google Login Click", Toast.LENGTH_SHORT).show()

                    }
                    1->{

                    }
                }

            },
            onSignUpClick = {  },
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)

        )

    }

}

@Composable
fun AlternativeLoginOptions(
    onIconClick: (index: Int) -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val iconList = listOf(
        R.drawable.icon_google,
        R.drawable.icon_instagram
    )
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.or))
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            iconList.forEachIndexed { index, iconResId ->
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = "Alternatives",
                    modifier = Modifier
                        .size(36.dp)
                        .clickable { onIconClick(index) }
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
        Row() {
            Text(text = stringResource(id = R.string.no_acc))

            TextButton(onClick = onSignUpClick) {
                Text(text = stringResource(id = R.string.sign_up))
            }
        }
    }
}