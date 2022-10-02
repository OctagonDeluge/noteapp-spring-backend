import React, {useEffect} from "react";
import {useForm} from "@mantine/form";
import {PasswordInput, TextInput, Group, Button, Container, useMantineTheme, Text} from "@mantine/core";
import {showNotification} from "@mantine/notifications";
import axios from "axios";
import {useNavigate, Link} from "react-router-dom"

function SignIn({isAuth, setAuth}) {

    const formData = new FormData();
    const navigate = useNavigate();
    const theme = useMantineTheme();

    const form = useForm({
        initialValues: {
            email: '',
            password: '',
        },
        validate: {
            email: (value) => (/^[a-zA-Z0-9]+@(?:[a-zA-Z0-9]+\.)+[A-Za-z]+$/.test(value) ? null : 'Невалидный email'),
            password: (value) => (value.length < 6 ? "Пароль должен содержать не менее 6 символов" : null),
        },
    });

    const setStorage = (values) => {
        sessionStorage.setItem("user", values.user.username);
        sessionStorage.setItem("authorized", values.user.authorized);
    }

    useEffect(() => {
        console.log(isAuth)
        if(isAuth === true) {
            navigate("/notes")
        }
    }, [isAuth])

    const handleSubmit = async (values) => {
        formData.append("username", values.email);
        formData.append("password", values.password);

        axios.post("/login", formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
            .then(res => {
                setStorage(res.data)
                setAuth(res.data.user.authorized);
            })
            .catch(res => {
                showNotification({
                    title: 'Failed to login',
                    message: res.toString(),
                    color: "red"
                })
            })
    }

    return (
        <div>
            <Container style={{width: 500, height: '100vh', background: theme.fn.rgba('#ffa630', 1)}}>
                <form style={{paddingTop: 200}} onSubmit={form.onSubmit(handleSubmit)}>

                    <TextInput
                        required
                        label="Почта"
                        placeholder="your@email.com"
                        {...form.getInputProps('email')}
                    />

                    <PasswordInput
                        required
                        label="Пароль"
                        placeholder="Password"
                        {...form.getInputProps('password')}
                    />

                    <Group position="center" mt="md" >
                        <Button style={{backgroundColor: theme.fn.rgba('#d7e8ba', 1), color: "black"}} type="submit">
                            Войти
                        </Button>
                            <Text style={{fontFamily: 'Greycliff CF, sans-serif'}}>
                                Нет аккаунта ?
                            </Text>
                            <Link to={"/registration"} style={{
                                textDecoration: "unset",
                                color: "white",
                                fontFamily: 'Greycliff CF, sans-serif'
                            }}>
                                Создать аккаунт
                            </Link>
                    </Group>
                </form>
            </Container>
        </div>
    )
}

export default SignIn;