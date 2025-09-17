package igor.second.spaceapp.appsettings

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import igor.second.spaceapp.R
import kotlinx.coroutines.launch

@Composable
fun UserInfoDialog(
    dialogShowValue: MutableState<Boolean>,
    dataStoreManager: DataStoreManager,
    userGenerationLevel: MutableState<Int>,
    userMoneyValue: MutableState<Int>,
    bronzeValue1: MutableState<Int>,
    bronzeValue2: MutableState<Int>,
    bronzeValue3: MutableState<Int>,
    bronzeValue4: MutableState<Int>,
    bronzeValue5: MutableState<Int>,
    bronzeValue6: MutableState<Int>,
    bronzeValue7: MutableState<Int>,
    bronzeValue8: MutableState<Int>,
    silverValue1: MutableState<Int>,
    silverValue2: MutableState<Int>,
    silverValue3: MutableState<Int>,
    silverValue4: MutableState<Int>,
    silverValue5: MutableState<Int>,
    silverValue6: MutableState<Int>,
    silverValue7: MutableState<Int>,
    silverValue8: MutableState<Int>,
    goldValue1: MutableState<Int>,
    goldValue2: MutableState<Int>,
    goldValue3: MutableState<Int>,
    goldValue4: MutableState<Int>,
    goldValue5: MutableState<Int>,
    goldValue6: MutableState<Int>,
    goldValue7: MutableState<Int>,
    goldValue8: MutableState<Int>,
    diamondValue1: MutableState<Int>,
    diamondValue2: MutableState<Int>,
    diamondValue3: MutableState<Int>,
    diamondValue4: MutableState<Int>,
    diamondValue5: MutableState<Int>,
    diamondValue6: MutableState<Int>,
    diamondValue7: MutableState<Int>,
    diamondValue8: MutableState<Int>,
    platinumValue1: MutableState<Int>,
    platinumValue2: MutableState<Int>,
    platinumValue3: MutableState<Int>,
    platinumValue4: MutableState<Int>,
    platinumValue5: MutableState<Int>,
    platinumValue6: MutableState<Int>,
    platinumValue7: MutableState<Int>,
    platinumValue8: MutableState<Int>,
    epicValue1: MutableState<Int>,
    epicValue2: MutableState<Int>,
    epicValue3: MutableState<Int>,
    epicValue4: MutableState<Int>,
    epicValue5: MutableState<Int>,
    epicValue6: MutableState<Int>,
    epicValue7: MutableState<Int>,
    epicValue8: MutableState<Int>,
    userName: MutableState<String>
) {

    val coroutine = rememberCoroutineScope()
    var textFieldText by remember { mutableStateOf("") }
    val context = LocalContext.current

    if (userName.value == ""){
        Dialog(onDismissRequest = { dialogShowValue.value = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(color = Color(0xFF87CEFA))
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Text(
                        stringResource(R.string.enter_name),
                        fontSize = 24.sp,
                        color = Color.Black
                    )
                    Text(
                        stringResource(R.string.name_not_change),
                        color = Color.DarkGray,
                        fontSize = 12.sp
                    )
                    OutlinedTextField(
                        value = textFieldText,
                        onValueChange = { textFieldText = it.take(10) },
                        modifier = Modifier,
                        textStyle = TextStyle(
                            fontSize = 24.sp,
                            color = Color(0xFF2D3748),
                            textAlign = TextAlign.Center
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        ),
                        visualTransformation = VisualTransformation.None,
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color(0xFF2D3748),
                            unfocusedTextColor = Color(0xFF2D3748),
                            cursorColor = Color(0xFF4299E1),
                            focusedContainerColor = Color(0xFFF7FAFC),
                            unfocusedContainerColor = Color(0xFFF7FAFC),
                            focusedIndicatorColor = Color(0xFF4299E1),
                            unfocusedIndicatorColor = Color(0xFFCBD5E0),
                            focusedLabelColor = Color(0xFF4299E1),
                            unfocusedLabelColor = Color(0xFF718096),
                            focusedPlaceholderColor = Color(0xFFA0AEC0),
                            unfocusedPlaceholderColor = Color(0xFFA0AEC0),
                        ),
                        shape = androidx.compose.material3.MaterialTheme.shapes.medium,
                        placeholder = {
                            Text(
                                stringResource(R.string.max_symbols),
                                fontSize = 18.sp,
                                color = Color(0xFFA0AEC0),
                                textAlign = TextAlign.Center
                            )
                        }
                    )
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(onClick = {
                            userName.value = "user:${(9999..1000000).random()}"
                            textFieldText = userName.value
                        }) {
                            Text(
                                stringResource(R.string.auto_generation),
                                color = Color.DarkGray
                            )
                        }
                        Button(onClick = {
                            userName.value = textFieldText
                            coroutine.launch {
                                dataStoreManager.saveSettings(
                                    SettingData(
                                        userGenerationLevel = userGenerationLevel.value,
                                        userMoneyValue = userMoneyValue.value,
                                        userName = userName.value,
                                        bronzeValue1 = bronzeValue1.value,
                                        bronzeValue2 = bronzeValue2.value,
                                        bronzeValue3 = bronzeValue3.value,
                                        bronzeValue4 = bronzeValue4.value,
                                        bronzeValue5 = bronzeValue5.value,
                                        bronzeValue6 = bronzeValue6.value,
                                        bronzeValue7 = bronzeValue7.value,
                                        bronzeValue8 = bronzeValue8.value,
                                        silverValue1 = silverValue1.value,
                                        silverValue2 = silverValue2.value,
                                        silverValue3 = silverValue3.value,
                                        silverValue4 = silverValue4.value,
                                        silverValue5 = silverValue5.value,
                                        silverValue6 = silverValue6.value,
                                        silverValue7 = silverValue7.value,
                                        silverValue8 = silverValue8.value,
                                        goldValue1 = goldValue1.value,
                                        goldValue2 = goldValue2.value,
                                        goldValue3 = goldValue3.value,
                                        goldValue4 = goldValue4.value,
                                        goldValue5 = goldValue5.value,
                                        goldValue6 = goldValue6.value,
                                        goldValue7 = goldValue7.value,
                                        goldValue8 = goldValue8.value,
                                        diamondValue1 = diamondValue1.value,
                                        diamondValue2 = diamondValue2.value,
                                        diamondValue3 = diamondValue3.value,
                                        diamondValue4 = diamondValue4.value,
                                        diamondValue5 = diamondValue5.value,
                                        diamondValue6 = diamondValue6.value,
                                        diamondValue7 = diamondValue7.value,
                                        diamondValue8 = diamondValue8.value,
                                        platinumValue1 = platinumValue1.value,
                                        platinumValue2 = platinumValue2.value,
                                        platinumValue3 = platinumValue3.value,
                                        platinumValue4 = platinumValue4.value,
                                        platinumValue5 = platinumValue5.value,
                                        platinumValue6 = platinumValue6.value,
                                        platinumValue7 = platinumValue7.value,
                                        platinumValue8 = platinumValue8.value,
                                        epicValue1 = epicValue1.value,
                                        epicValue2 = epicValue2.value,
                                        epicValue3 = epicValue3.value,
                                        epicValue4 = epicValue4.value,
                                        epicValue5 = epicValue5.value,
                                        epicValue6 = epicValue6.value,
                                        epicValue7 = epicValue7.value,
                                        epicValue8 = epicValue8.value
                                    )
                                )
                            }
                            coroutine.launch {
                                dialogShowValue.value = false
                            }
                        },
                            modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(start = 36.dp, top = 8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF2196F3),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(12.dp),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 8.dp,
                                pressedElevation = 4.dp,
                                disabledElevation = 0.dp
                            ),
                            border = BorderStroke(2.dp, Color(0xFF1976D2))) {
                            Text("Save")
                        }
                    }
                }
            }
        }
    } else {
        Toast.makeText(context, "${userName.value} your name is not change", Toast.LENGTH_SHORT).show()
        dialogShowValue.value = false
    }
}