import { useState } from "react";
import "../NapTien.css"; 

export default function NapTien() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [userID, setUserID] = useState(null); 
    const [user, setUser] = useState(null); 
    const [error, setError] = useState("");
    const [dulieu, setDulieu] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [tien, setTien] = useState(0);

    const fetchUserDetails = async (userID) => {
        try {
            const userResponse = await fetch(`http://localhost:8082/api/tien/quan_ly/${userID}`, { method: "GET" });
            if (!userResponse.ok) throw new Error(`Failed to fetch user data: ${userResponse.status}`);

            const userDetails = await userResponse.json();
            setUser(userDetails.data);
            setError(""); 
        } catch (err) {
            setError(err.message);
            console.error(err);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault(); 
        try {
            const response = await fetch("http://localhost:8082/api/user/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json", 
                },
                body: JSON.stringify({ username, password }),
            });

            if (!response.ok) throw new Error(`Login failed: ${response.status}`);

            const loginData = await response.json();
            setUserID(loginData.data.userID);
            fetchUserDetails(loginData.data.userID);
        } catch (err) {
            setError(err.message);
            console.error(err);
        }
    };

    const handleNapTien = async (e) => {
        e.preventDefault();
        if(tien<0){
            alert("Tiền nạp không hợp lệ!!!");
            return ;
        }
        try {
            const response = await fetch("http://localhost:8082/api/nap_tien/recharge", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json", 
                },
                body: JSON.stringify({ userID, amount: tien }),
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || `Nạp tiền thất bại với mã lỗi: ${response.status}`);
            }

            const data = await response.json();
            setDulieu(data.data); 
            setShowModal(true);

            setTien(0);
            fetchUserDetails(userID);
        } catch (err) {
            setError(err.message);
            console.error(err);
        }
    };

    const closeModal = () => {
        setShowModal(false); 
    };

    return (
        <div className="container">
            <h1 className="title">Đăng nhập tài khoản</h1>
            <form className="form-login" onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="username">User name:</label>
                    <input 
                        id="username" 
                        name="username" 
                        type="text" 
                        value={username} 
                        onChange={(e) => setUsername(e.target.value)} 
                        className="input-field"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password:</label>
                    <input 
                        id="password" 
                        name="password" 
                        type="password" 
                        value={password} 
                        onChange={(e) => setPassword(e.target.value)} 
                        className="input-field"
                    />
                </div>
                <button className="btn-primary" type="submit">Đăng nhập</button>
            </form>

            {error && <div className="error-message">{error}</div>}

            {user && (
                <div className="user-info">
                    <h2>Thông tin tài chính của {user.username}</h2>
                    <div className="user-details">
                        <p><strong>Tên học viên:</strong> {user.username}</p>
                        <p><strong>Số dư:</strong> {user.soDu}</p>
                        <p><strong>Công nợ:</strong> {user.congNo}</p>
                        <p><strong>Cần thanh toán:</strong> {user.canThanhToan}</p>
                        <form onSubmit={handleNapTien} className="form-recharge">
                            <label>Nhập số tiền muốn nạp:</label>
                            <input 
                                type="number" 
                                value={tien} 
                                onChange={(e) => setTien(e.target.value)} 
                                className="input-field"
                            />
                            <button className="btn-primary" type="submit">Nạp Tiền</button>
                        </form>
                    </div>
                </div>
            )}

            {showModal && (
                <div className="modal">
                    <div className="modal-content">
                        <span className="close-button" onClick={closeModal}>&times;</span>
                        <h2>Thông báo</h2>
                        <p>Thông tin nạp tiền</p>
                        <p><strong>Mã giao dịch:</strong> {dulieu?.maGiaoDich}</p>
                        <p><strong>Tình trạng:</strong> {dulieu?.tinhTrang}</p>
                        <p><strong>Lời nhắn:</strong> {dulieu?.loiNhan}</p>
                    </div>
                </div>
            )}
        </div>
    );
}
