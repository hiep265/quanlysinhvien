import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import KetQuaHP from "./components/KetQuaHP";
import NapTien from "./components/NapTien";
import QLTaiChinh from "./components/QLTaiChinh";
import TrangChu from "./components/TrangChu";
import HocPhan from "./components/HocPhan";
import DangKyHP from "./components/DangKyHP";
import LichThi from "./components/LichThi";
import Login from "./components/Login";


function App() {
  

  return (
    <Router>
      
      <div >
        <Routes>
          <Route path="/" element={<Login/>}/>
          <Route path="/TrangChu" element={<TrangChu/>}/>

          <Route path="/DangKyHP" element={<DangKyHP/>}/>
          <Route path="/HocPhan" element={<HocPhan/>}/>
          <Route path="/KetQuaHP" element={<KetQuaHP/>}/>
          <Route path="/NapTien" element={<NapTien/>}/>
          <Route path="/QLTaiChinh" element={<QLTaiChinh/>}/>
          <Route path="/LichThi" element={<LichThi/>}/>
        </Routes>
      </div>
      
    </Router>
  )
}

export default App
