import {Link} from "react-router-dom";
export default function Header(){
    return(
        <nav>
            <ul className="header">
                
                <li><Link className="link" to="/TrangChu">Trang chủ</Link></li>
                <li><Link className="link" to="/KetQuaHP">Kết Quả Học Phần</Link></li>
                <li><Link className="link" to="/NapTien">Nạp Tiền Vào Tài Khoản</Link></li>
                <li><Link className="link" to="/QLTaiChinh">Quản Lý Tài Chính</Link></li>
            </ul>
        </nav>
    )
}