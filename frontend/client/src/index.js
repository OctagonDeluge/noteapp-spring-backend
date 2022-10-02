import ReactDOM from 'react-dom/client';
import {BrowserRouter} from "react-router-dom";
import {NotificationsProvider} from "@mantine/notifications";
import "./index.css"
import App from "./App";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <BrowserRouter>
        <NotificationsProvider>
            <App />
        </NotificationsProvider>
    </BrowserRouter>
);
