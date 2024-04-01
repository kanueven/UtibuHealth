package com.rae.utibuhealth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rae.utibuhealth.ui.theme.Primary
import com.rae.utibuhealth.ui.theme.Secondary
import com.rae.utibuhealth.ui.theme.UtibuHealthTheme

@Composable
fun MyHeaderText(
    text: String,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        style = MaterialTheme.typography.displayMedium,
        fontWeight = FontWeight.Bold,
        modifier = modifier)

}
@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    labelValue: String,
    leadingIcon: ImageVector? =null,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None
){
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange =onValueChange,
        leadingIcon = {if (leadingIcon != null) Icon(imageVector = leadingIcon,null) },
        label = { Text(text = labelValue)},
        keyboardOptions = KeyboardOptions(keyboardType=keyboardType),
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(10.dp)
    )
}
@Composable
fun MyPasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    labelValue: String,
    leadingIcon: ImageVector? =null,
    trailingIcon: () -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None
){
    val passwordVisible = remember{ mutableStateOf(false) }
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange =onValueChange,
        leadingIcon = {if (leadingIcon != null) Icon(imageVector = leadingIcon,null)},

        label = { Text(text = labelValue)},
        keyboardOptions = KeyboardOptions(keyboardType=keyboardType),
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(10.dp)
    )
}
@Composable
fun ButtonComponent(value: String,
                    onClick: () -> Unit,
                    isFieldEmpty:Boolean

){
    Button(onClick = {},
        modifier = Modifier.fillMaxWidth(),
        enabled = isFieldEmpty,
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
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
            Text(text = value)

        }

    }
}
@Composable
fun TopAppBar(title:String,icon:ImageVector,onIconClick: () -> Unit){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = MaterialTheme.colorScheme.onSurface)
    ){
        Image(
            imageVector =icon ,
            contentDescription =null,
            colorFilter = ColorFilter
                .tint(MaterialTheme.colorScheme.onPrimary),
            modifier = Modifier
                .clickable(onClick = onIconClick)
                .padding(16.dp)
                .align(Alignment.CenterVertically)
        )

        Text(text = title,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .padding(start = 16.dp, end = 16.dp))
    }

}
@Preview
@Composable
private fun TopAppBarPreview() {
    UtibuHealthTheme {
        TopAppBar(
            title = "Hivpill",
            icon = Icons.Filled.ArrowBack,
            onIconClick = {}
        )
    }
}