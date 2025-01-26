package DataAccess.DTO;

public class AutenticacionOTPDTO {

    private Integer idAutenticacionOTP;
    private String otp;
    private String otpExpiracion;
    private String fechaCrea;

    public AutenticacionOTPDTO() {
    }

    public AutenticacionOTPDTO(Integer idAutenticacionOTP, String otp, String otpExpiracion, String fechaCrea) {
        this.idAutenticacionOTP = idAutenticacionOTP;
        this.otp = otp;
        this.otpExpiracion = otpExpiracion;
        this.fechaCrea = fechaCrea;
    }

    public Integer getIdAutenticacionOTP() {
        return idAutenticacionOTP;
    }

    public void setIdAutenticacionOTP(Integer idAutenticacionOTP) {
        this.idAutenticacionOTP = idAutenticacionOTP;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getOtpExpiracion() {
        return otpExpiracion;
    }

    public void setOtpExpiracion(String otpExpiracion) {
        this.otpExpiracion = otpExpiracion;
    }

    public String getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(String fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "\n IdAutenticacionOTP: " + getIdAutenticacionOTP()
                + "\n OTP: " + getOtp()
                + "\n OTP Expiracion: " + getOtpExpiracion()
                + "\n Fecha Crea: " + getFechaCrea();
    }
}
