import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from './pages/login/login';
import 'bootstrap/dist/css/bootstrap.min.css';
import Signup from "./pages/Signup/Signup";


export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" >
          <Route index element={<Login />} />
          <Route path='/signup' element={<Signup />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);