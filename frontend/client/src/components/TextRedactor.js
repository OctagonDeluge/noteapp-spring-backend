import {Container, TextInput, Textarea, Center} from "@mantine/core"
import axios from "axios";
import {showNotification} from "@mantine/notifications";

function TextRedactor({note, setNote, notes, setNotes}) {

    const sendPostOrPutReq = () => {
        if (note.id !== undefined) {
            axios.put('/notes/' + note.id, note)
                .then((res) => {
                    let temp = [...notes];
                    let index = temp.findIndex(el => el.id === note.id);
                    temp[index] = note;
                    setNotes(temp);
                })
                .catch(res => {
                    showNotification({
                        title: 'Failed to save note',
                        message: 'Empty title or content fields',
                        color: "red"
                    })
                })
        } else {
            axios.post('/notes/', note)
                .then((res) => {
                    setNote(res.data);
                    setNotes([...notes, res.data]);
                })
                .catch(res => {
                    showNotification({
                        title: 'Failed to save note',
                        message: 'Empty title or content fields',
                        color: "red"
                    })
                })
        }
    }

    const handleChange = (e) => {
        const value = e.target.value;
        setNote({...note, [e.target.name]: value})
    }

    const deleteNote = (id) => {
        axios.delete('/notes/' + id)
            .then((response) => {
                console.log(response);
                let temp = [...notes];
                let index = temp.findIndex(el => el.id === id);
                temp.splice(index,1);
                setNotes(temp);
                setNote({
                    caption: "",
                    content: ""
                })
            })
            .catch(res => {
                showNotification({
                    title: 'Failed to delete note',
                    message: 'No note was selected',
                    color: "red"
                })
            });
    }

    return (
        <div className="textRedactor">
            <Container className="toolBox">
                <Center style={{height: 100}}>
                    <button onClick={() => {
                        sendPostOrPutReq();
                    }}>
                        Сохранить
                    </button>

                    <button onClick={() => {
                        deleteNote(note.id);
                    }}>
                        Удалить
                    </button>
                </Center>
            </Container>
            <div className="inputs">
                <div className="sub-inputs">
                    <TextInput
                        placeholder="Заголовок"
                        required
                        name="caption"
                        value={note.caption}
                        onChange={(e) => handleChange(e)}
                    />
                    <Textarea
                        minRows={26}
                        placeholder="Заметка"
                        required
                        name="content"
                        value={note.content}
                        onChange={(e) => handleChange(e)}
                    />
                </div>
            </div>
        </div>
    )
}

export default TextRedactor;