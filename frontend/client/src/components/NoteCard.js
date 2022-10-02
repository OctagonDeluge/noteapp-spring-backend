import React, {useEffect} from "react";
import {ScrollArea, Text, Title, useMantineTheme} from "@mantine/core";
import "../assets/styles/NoteCard.css";
import axios from "axios";

function NoteCard({notes, setNotes, note, setNote}) {
    const theme = useMantineTheme();

    useEffect(() => {
        axios.get('/notes').then(
            res => {
                setNotes(res.data);
            })
    }, []);

    return (
        <div className="noteCard">
            <div className="caption">
                <Title style={{paddingTop: 20, marginLeft: 20}}>Все заметки</Title>
                <Text style={{paddingTop: '20%', marginLeft: 20, color: theme.fn.rgba('rgba(23,22,22,0.66)', 0.71)}}>
                    Всего заметок: {notes.length}
                </Text>
            </div>
            <button onClick={() => {
                setNote({
                    caption: "",
                    content: ""
                })
            }}>
                Добавить запись
            </button>
            <ScrollArea style={{height: '75%'}} offsetScrollbars scrollbarSize={8} scrollHideDelay={500}>
                {notes.map((data) => (
                    <div className="note" key={data.id} onClick={() => {
                        setNote({
                            id: data.id,
                            caption: data.caption,
                            content: data.content
                        })

                    }}>
                        <Text lineClamp={1} weight={700} size='lg'>{data.caption}</Text>
                        <Text lineClamp={5} >{data.content}</Text>
                    </div>
                ))}
            </ScrollArea>
        </div>
    )
}

export default NoteCard;