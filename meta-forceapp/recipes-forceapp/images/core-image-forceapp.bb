#inherit core-image
#CORE_IMAGE_EXTRA_INSTALL += "forceapp"
#CORE_IMAGE_EXTRA_INSTALL += "openssh"
#CORE_IMAGE_EXTRA_INSTALL += "nano"
#inherit extrausers
#PASSWD = "\$5\$2WoxjAdaC2\$l4aj6Is.EWkD72Vt.byhM5qRtF9HcCM/5YpbxpmvNB5"
#EXTRA_USERS_PARAMS = "usermod -p '${PASSWD}' root;"


require recipes-core/images/core-image-base.bb

CORE_IMAGE_EXTRA_INSTALL += "openssh openssl openssh-sftp-server"
CORE_IMAGE_EXTRA_INSTALL += "ethtool"
CORE_IMAGE_EXTRA_INSTALL += "python3"
CORE_IMAGE_EXTRA_INSTALL += "rpi-gpio"

inherit extrausers
PASSWD = "\$5\$2WoxjAdaC2\$l4aj6Is.EWkD72Vt.byhM5qRtF9HcCM/5YpbxpmvNB5"
EXTRA_USERS_PARAMS = "usermod -p '${PASSWD}' root;"