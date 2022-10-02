import {Route, Routes} from "react-router-dom";
import SignIn from "./pages/SignIn";
import Note from "./pages/Note";
import Schedule from "./pages/Schedule";
import SignUp from "./pages/SignUp";
import NotFound404 from "./pages/errors/NotFound404";
import React, {useState} from "react";
import Unauthorized401 from "./pages/errors/Unauthorized401";

function App() {
    const [isAuth, setAuth] = useState(JSON.parse(sessionStorage.getItem("authorized")));
    return (
        <div>
            {
                isAuth === true ?
                <Routes>
                    <Route path="/notes" element={<Note setAuth={setAuth}/>}/>
                    <Route path="/calendar" element={<Schedule setAuth={setAuth}/>}/>
                    <Route path="/login" element={<SignIn isAuth={isAuth} setAuth={setAuth}/>}/>
                    <Route path="/registration" element={<SignUp/>}/>
                    <Route path="*" element={<NotFound404/>}/>
                </Routes>
                :
                <Routes>
                    <Route path="/login" element={<SignIn isAuth={isAuth} setAuth={setAuth}/>}/>
                    <Route path="/registration" element={<SignUp/>}/>
                    <Route path="*" element={<Unauthorized401/>}/>
                </Routes>
            }
        </div>
    )
}

export default App;