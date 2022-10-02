import React from "react";
import {Navbar, Avatar, Center, Anchor, createStyles, Text} from '@mantine/core'
import {Link, useNavigate} from "react-router-dom";
import "../assets/styles/Note.css"
import axios from "axios";

const useStyles = createStyles((theme) => {
    return {
        link: {
            fontSize: 24,
            marginLeft: 20,
            marginTop: 20,
            cursor: "pointer",
            fontFamily: 'Montserrat, serif',
            color: theme.fn.rgba('rgb(255,255,255)', 1),
            textDecoration: "unset",
            '&:hover': {
                textDecoration: "underline"
            },
        },
    };
})

function ProfileSection({setAuth}) {
    const {classes} = useStyles();
    const navigate = useNavigate();

    return (
        <Navbar
            width={{base: 330}}
            styles={{root: {backgroundColor: '#564256'}}}
        >
            <Navbar.Section>
                <Center style={{width: 330, height: 200}}>
                    <Avatar
                        src={null}
                        alt="no image here"
                        color="indigo"
                        size={150}
                        radius={100}
                    />
                </Center>
            </Navbar.Section>
            <Navbar.Section>
                <Center style={{width: 330, height: 20}}>
                    <Text size='xl' color='white'>{sessionStorage.getItem("user")}</Text>
                </Center>
            </Navbar.Section>
            <Navbar.Section>
                <div className="links">
                    <Anchor component={Link} to="/notes" className={classes.link}>
                        Заметки
                    </Anchor>
                    <Anchor component={Link} to="/calendar" className={classes.link}>
                        Календарь
                    </Anchor>
                    <a className={classes.link} onClick={() => {
                        axios.post("/logout").then(res => {
                            setAuth(false);
                            sessionStorage.clear();
                            navigate("/login");
                        })
                    }}>
                        Выход
                    </a>
                </div>

            </Navbar.Section>
        </Navbar>
    );
}

export default ProfileSection;