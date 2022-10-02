import React, { useState} from "react"
import NoteCard from "../components/NoteCard";
import ProfileSection from "../components/ProfileSection"
import TextRedactor from "../components/TextRedactor"
import '../assets/styles/Note.css';

function Note({setAuth}) {
    const [note, setNote] = useState({
        caption: "",
        content: ""
    });
    const [notes, setNotes] = useState([]);

    return (
        <div className="main">
            <ProfileSection setAuth={setAuth}/>
            <NoteCard note={note} setNote={setNote} notes={notes} setNotes={setNotes}/>
            <TextRedactor note={note} setNote={setNote} notes={notes} setNotes={setNotes}/>
        </div>
    )
}

export default Note;