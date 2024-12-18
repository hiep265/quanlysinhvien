import { useEffect, useState } from "react";


export default function NapTien() {
    
    const [userID, setUserID] = useState(null); 
    const [user, setUser] = useState(null); 
    const [error, setError] = useState("");
    const [dulieu, setDulieu] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [hocphan, setHocphans] = useState(null)
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

     const getHPDetails = async(userID)=>{
            
        try{
            const HP = await fetch(`http://localhost:8082/api/dang_ky/HP_No/${userID}`, 
                {
                    method: "GET"
                }
            )
            
            const HPdata = await HP.json();
            setHocphans(HPdata.data);

        } catch (err) {
            setError(err.message);
            console.error(err);
        }
    
    } 
    useEffect(()=>{
        const storeUserID = localStorage.getItem('userID')
        setUserID(storeUserID);
        fetchUserDetails(userID);
        getHPDetails(userID);
    }, [userID])

    const handleThanhToan = async (dangKyID) => {
        try {
            const response = await fetch(`http://localhost:8082/api/tien/thanh_toan_hp_no/${userID}/${dangKyID}`, {
                method: "PUT"
            });

            if (!response.ok) {
                throw new Error(`Lỗi thanh toán: ${response.status}`);
            }

            const DL = await response.json();
            var mess = DL.data;
            alert(mess);

            await fetchUserDetails(userID);

            const HP = await fetch(`http://localhost:8082/api/dang_ky/HP_No/${userID}`, { method: "GET" });
            if (!HP.ok) throw new Error(`Lỗi lấy danh sách học phần: ${HP.status}`);
            const HPdata = await HP.json();
            setHocphans(HPdata.data);

        } catch (err) {
            setError(err.message);
            console.error(err);
        }
    };

    const handleNapTien = async (e) => {
        e.preventDefault();
        if (tien < 0 && !/^\d+$/.test(tien)) {
            alert("Tiền nạp không hợp lệ!!!");
            return;
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
        <div className="container mt-5">
           <h1 className="text-center mb-4">Quản lý giao dịch</h1>
        

            {error && <div className="alert alert-danger mt-3">{error}</div>}

            {hocphan && hocphan.length > 0 && (
                <div className="mt-4">
                    <h3>Danh sách học phần chưa thanh toán</h3>
                    <table className="table table-striped mt-3">
                        <thead>
                            <tr>
                                <th>Tên Học Phần</th>
                                <th>Tên Giáo Viên</th>
                                <th>TX1</th>
                                <th>TX2</th>
                                <th>Điểm cuối kì</th>
                                <th>Số tín</th>
                                <th>Thanh toán</th>
                            </tr>
                        </thead>
                        <tbody>
                            {hocphan.map((ket_qua) => (
                                <tr key={ket_qua.dangKyID}>
                                    <td>{ket_qua.dangKyID}</td>
                                    <td>{ket_qua.tenGiaoVien}</td>
                                    <td>{ket_qua.tx1}</td>
                                    <td>{ket_qua.tx2}</td>
                                    <td>{ket_qua.diem}</td>
                                    <td>{ket_qua.hocPhan.soTinChi}</td>
                                    <td><button className="btn btn-success" onClick={() => { handleThanhToan(ket_qua.dangKyID) }}>Thanh toán</button></td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )}

            {user && (
                <div className="user-info mt-4 ">
                    <h2>Thông tin tài chính của {user.username}</h2>
                    <div className="user-details ">
                        <p><strong>Tên học viên:</strong> {user.username}</p>
                        <p><strong>Số dư:</strong> {user.soDu}</p>
                        <p><strong>Công nợ:</strong> {user.congNo}</p>
                        <p><strong>Cần thanh toán:</strong> {user.canThanhToan}</p>
                        <form onSubmit={handleNapTien} className="form-recharge mt-4">
                            <div className="mb-3">
                                <label className="form-label ">Nhập số tiền muốn nạp:</label>
                                <input 
                                    type="number" 
                                    value={tien} 
                                    onChange={(e) => setTien(e.target.value)} 
                                    className="form-control w-50"
                                />
                            </div>
                            <button className="  justify-content-center btn btn-primary w-50" type="submit">Nạp Tiền</button>
                        </form>
                    </div>
                </div>
            )}

            {showModal && (
                <div className="modal" style={{ display: "block" }}>
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title">Thông báo</h5>
                                <button type="button" className="btn-close" onClick={closeModal}></button>
                            </div>
                            <div className="modal-body">
                                <p>Thông tin nạp tiền</p>
                                <p><strong>Mã giao dịch:</strong> {dulieu?.maGiaoDich}</p>
                                <p><strong>Tình trạng:</strong> {dulieu?.tinhTrang}</p>
                                <p><strong>Lời nhắn:</strong> {dulieu?.loiNhan}</p>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}
